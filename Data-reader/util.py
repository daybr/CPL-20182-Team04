import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def read_data(file_path):
    column_names = ['x-axis', 'y-axis', 'z-axis', 'breathe', 'ppg']
    data = pd.read_csv(file_path, header = None, delim_whitespace=True, names = column_names)
    
    
    '''
    data = data[~data.index.duplicated(keep='last')]
    data = data[(data.index >= 1) & (data.index <= 3000)]
    data = data[~data.index.duplicated(keep='last')]
    data['timestamp'] = pd.date_range('2017-01-01', periods=3000, freq='ms')
    data = data.set_index(["timestamp"])

    data = data.rolling(window=300).mean()[300:]
    data = feature_normalize(data)
    data = data + 0.08 * np.random.uniform(size=data.shape)
    
    return data
    '''
    
    data = data[~data.index.duplicated(keep='last')]
    data = data[(data.index >= 1) & (data.index <= 290000)]
    data['timestamp'] = pd.date_range('2017-01-01', periods=290000, freq='10ms')
    data = data.set_index(["timestamp"])

    data = data.resample('1s').mean()
    data = feature_normalize(data)

    return data
    

def refine_data(data):
    # drop meaningless values
    data.dropna(axis=0, how='any', inplace= True)

    # down sample to 1/100
    #data = data.resample('s').mean()

    # normalize features 
    data['x-axis'] = feature_normalize(data['x-axis'])
    data['y-axis'] = feature_normalize(data['y-axis'])
    data['z-axis'] = feature_normalize(data['z-axis'])
    data['breathe'] = feature_normalize(data['breathe'])

    return data


def feature_normalize(feature):
    mu = np.mean(feature ,axis = 0)
    sigma = np.std(feature ,axis = 0)
    return (feature - mu) / sigma

def plot_axis(ax, x, y, title):
    ax.plot(x, y)
    ax.set_title(title)
    ax.xaxis.set_visible(False)
    ax.set_ylim([min(y) - np.std(y), max(y) + np.std(y)])
    ax.set_xlim([min(x), max(x)])
    ax.grid(True)
    
def plot_activity(activity,data):
    #fig, (ax0, ax1, ax2, ax3) = plt.subplots(nrows = 4, figsize = (15, 10), sharex = True)
    fig, ax3 = plt.subplots(nrows = 1, figsize = (10, 5), sharex = True)
    #plot_axis(ax0, data.index, data['x-axis'], 'x-axis')
    #plot_axis(ax2, data.index, data['z-axis'], 'z-axis')
    #lot_axis(ax1, data.index, data['y-axis'], 'y-axis')
    plot_axis(ax3, data.index, data['breathe'], 'breathe')
    plt.subplots_adjust(hspace=0.2)
    fig.suptitle(activity)
    plt.subplots_adjust(top=0.90)
    plt.show()
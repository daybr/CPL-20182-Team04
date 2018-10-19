import matplotlib.pyplot as plt
import numpy as np
import math
import pandas as pd
import tensorflow as tf

from scipy import stats


def read_data(file_path):
    column_names = ['x-axis', 'y-axis', 'z-axis', 'breathe', 'ppg']
    data = pd.read_csv(file_path, header = None, delim_whitespace=True, names = column_names)

    data = data[(data.index >= 1) & (data.index <= 350000)]
    data['timestamp'] = pd.date_range('2017-01-01', periods=350000, freq='10ms')
    data = data.set_index(["timestamp"])
    return data

def feature_normalize(dataset):
    mu = np.mean(dataset,axis = 0)
    sigma = np.std(dataset,axis = 0)
    return (dataset - mu)/sigma

def plot_axis(ax, x, y, title):
    ax.plot(x, y)
    ax.set_title(title)
    ax.xaxis.set_visible(False)
    ax.set_ylim([min(y) - np.std(y), max(y) + np.std(y)])
    ax.set_xlim([min(x), max(x)])
    ax.grid(True)
    
def plot_activity(activity,data):
    fig, (ax0, ax1, ax2, ax3) = plt.subplots(nrows = 4, figsize = (15, 10), sharex = True)
    plot_axis(ax0, data.index, data['x-axis'], 'x-axis')
    plot_axis(ax2, data.index, data['z-axis'], 'z-axis')
    plot_axis(ax1, data.index, data['y-axis'], 'y-axis')
    plot_axis(ax3, data.index, data['breathe'], 'breathe')
    plt.subplots_adjust(hspace=0.2)
    fig.suptitle(activity)
    plt.subplots_adjust(top=0.90)
    plt.show()

dataset = read_data('normal.txt')
dataset.dropna(axis=0, how='any', inplace= True)
# downsampling to 1/100
dataset = dataset.resample('s').mean()

dataset['x-axis'] = feature_normalize(dataset['x-axis'])
dataset['y-axis'] = feature_normalize(dataset['y-axis'])
dataset['z-axis'] = feature_normalize(dataset['z-axis'])
dataset['breathe'] = feature_normalize(dataset['breathe'])

from scipy import stats
dataset[(np.abs(stats.zscore(dataset)) < 1).all(axis=1)]

plot_activity('', dataset)

graph = tf.Graph()

with graph.as_default():
    inputs_ = tf.placeholder(tf.float32, [None, 3500, 3], name = 'inputs')
    labels_ = tf.placeholder(tf.float32, [None, 2], name = 'labels')
    keep_prob_ = tf.placeholder(tf.float32, name = 'keep')

    learing_rate_ = tf.placeholder(tf.float32, name = 'learning_rate')

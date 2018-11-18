import pandas as pd
import numpy as np
import tensorflow as tf
import os

from util import read_data, refine_data, plot_activity
from model import cnn_model_fn
from scipy import stats


apnea_training_path = 'training/apnea'
normal_training_path = 'validation/normal/'
snoring_training_path = 'validation/snoring/' 

file_path = os.path.join(apnea_training_path, 'data-0.txt')
data = read_data(file_path)
#data = refine_data(data)

plot_activity('', data)
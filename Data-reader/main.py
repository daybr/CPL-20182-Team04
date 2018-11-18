import pandas as pd
import numpy as np
import tensorflow as tf
import os

from util import read_data, refine_data
from model import cnn_model_fn
from scipy import stats

tf.logging.set_verbosity(tf.logging.INFO)


# 0=apnea, 1=normal, 2=snoring
dataset = []
labels = []

APNEA = 0
NORMAL = 1
SNORING = 2

apnea_training_path = 'training/apnea/'
normal_training_path = 'training/normal/'
snoring_training_path = 'training/snoring/' 

apnea_files = os.listdir(apnea_training_path)
apnea_files.sort()

for file in apnea_files:
    print('apnea-' + file)
    file_path = os.path.join(apnea_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    dataset.append(data)
    labels.append(APNEA)

normal_files = os.listdir(normal_training_path)
normal_files.sort()

for file in normal_files:
    print('normal-' + file)
    file_path = os.path.join(normal_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    dataset.append(data)
    labels.append(NORMAL)


snoring_files = os.listdir(snoring_training_path)
snoring_files.sort()

for file in snoring_files:
    print('snoring-' + file)
    file_path = os.path.join(snoring_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    dataset.append(data)
    labels.append(NORMAL)

validation_dataset = []
validation_labels = []

apnea_training_path = 'validation/apnea/'
normal_training_path = 'validation/normal/'
snoring_training_path = 'validation/snoring/' 

apnea_files = os.listdir(apnea_training_path)
apnea_files.sort()

for file in apnea_files:
    print('apnea-' + file)
    file_path = os.path.join(apnea_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    validation_dataset.append(data)
    validation_labels.append(APNEA)

normal_files = os.listdir(normal_training_path)
normal_files.sort()

for file in normal_files:
    print('normal-' + file)
    file_path = os.path.join(normal_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    validation_dataset.append(data)
    validation_labels.append(NORMAL)


snoring_files = os.listdir(snoring_training_path)
snoring_files.sort()

for file in snoring_files:
    print('snoring-' + file)
    file_path = os.path.join(snoring_training_path, file)
    data = read_data(file_path)
    data = refine_data(data)

    validation_dataset.append(data)
    validation_labels.append(NORMAL)

# plot_activity('', dataset)

# Create the Estimator
apnea_classifier = tf.estimator.Estimator(
    model_fn=cnn_model_fn)

# Set up logging for predictions
tensors_to_log = {"probabilities": "softmax_tensor"}
logging_hook = tf.train.LoggingTensorHook(
    tensors=tensors_to_log, every_n_iter=1)


SIZE = 2900

# handling data with map operation here
X = np.array(
    list(
        map(lambda record: record['breathe'].values.reshape(1, SIZE),
        dataset)
        )
)

A = X + 0.08 * np.random.uniform(size=X.shape)
B = X + 0.04 * np.random.uniform(size=X.shape)
C = X + 0.02 * np.random.uniform(size=X.shape)

X = np.concatenate((A, B, C), axis=0)

#noise = 0.0008*np.asarray(random.sample(range(0,2900),sample))

Y = np.vstack(labels)
Y = np.concatenate((Y, Y, Y), axis=0)

train_input_fn = tf.estimator.inputs.numpy_input_fn(
    x={"x": X },
    y=Y,
    batch_size=1,
    num_epochs=None,
    shuffle=True)


apnea_classifier.train(
        input_fn=train_input_fn,
        steps=2000,
        hooks=[logging_hook]
    )


# handling data with map operation here
X = np.array(
    list(
        map(lambda record: record['breathe'].values.reshape(1, SIZE),
        validation_dataset)
        )
)

Y = np.vstack(validation_labels)

# Evaluate the model and print results
eval_input_fn = tf.estimator.inputs.numpy_input_fn(
    x={"x": X },
    y=Y,
    num_epochs=1,
    shuffle=True)
eval_results = apnea_classifier.evaluate(input_fn=eval_input_fn)
print(eval_results)
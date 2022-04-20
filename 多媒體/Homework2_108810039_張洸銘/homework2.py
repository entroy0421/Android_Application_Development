from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
from sklearn.linear_model import LinearRegression
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis as QDA
from sklearn.metrics import confusion_matrix
import numpy as np
import numpy.linalg as nplg
import math

iris_x = open('iris_x.txt').readlines()
iris_y = open('iris_y.txt').readlines()

a = []
for i in iris_x:
    tmp = i.split('\t')[:4]
    a.append(tmp)

iris_x = np.array(a)
iris_x = iris_x.astype(np.float)

a = []
for i in iris_y:
    tmp = i.split('\n')[0:1]
    a.append(tmp)

iris_y = np.array(a)
iris_y = iris_y.astype(np.int)

x_train, x_test, y_train, y_test = train_test_split(iris_x, iris_y, test_size=0.2, random_state=20220413, stratify=iris_y)

print('x_train', x_train)
print('x_test', x_test)
print('y_train', y_train)
print('y_test', y_test)

print('x_train', len(x_train))
print('x_test', len(x_test))
print('y_train', len(y_train))
print('y_test', len(y_test))

mlr = LinearRegression()
mlr.fit(x_train, y_train)

y_predict = mlr.predict(x_test)
print(y_predict)

mse = mean_squared_error(y_test, y_predict)

print('mse =', mse)

class _QDA():
    def ___init__(self):
        self.mu=np.array([])
        self.cov=np.array([])

    def fit(self, data_train, label_train):
        self.classes = np.unique(label_train)
        mu, cov=[],[]
        for i in range(np.max(label_train)+1):
            pos = np.where(label_train==i)[0]
            tmp_data = data_train[pos,:]
            tmp_cov = np.cov(np.transpose(tmp_data))
            tmp_mu = np.mean(tmp_data,axis=0)
            mu.append(tmp_mu)
            cov.append(tmp_cov)
        self.mu = np.array(mu)
        self.cov = np.array(cov)

    def score(x_test, y_test):
        

    def predict(self, xtest):
        result = []
        for test in xtest:
            temp = []
            for i in range(len(self.mu)):
                value = -0.5*math.log(np.linalg.det(self.cov[i]))-0.5*np.dot(np.dot(
                    np.transpose(test-self.mu[i]), np.linalg.inv(self.cov[i])), (test-self.mu[i]))+math.log(np.pi)
                temp.append(value)
            temp = np.array(temp)
            result.append(np.argmax(temp))
        return result

clf = _QDA()
clf.fit(x_train, y_train)
predict = clf.predict(x_test)

print(predict)

clf = QDA()
clf.fit(x_train, y_train)

predict = clf.predict(x_test)
accuracy = clf.score(x_test, y_test)

print('accuracy =', accuracy)
print('confusion matrix =\n', confusion_matrix(y_test, predict))
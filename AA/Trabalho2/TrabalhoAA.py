import pandas as pd
import numpy as np
import sklearn
from sklearn import linear_model
from sklearn import model_selection
from sklearn.linear_model import LogisticRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.linear_model import RidgeClassifier


datest = pd.read_csv('test.csv')

dfid= datest['Id']


del datest['Program']
del datest['Id']
#del datest['Y4s1_grade']
#del datest['Y4s1_complete']
#del datest['Y4s1_enrol']
test = datest.values




datrain = pd.read_csv('train.csv') 
del datrain['Program']
del datrain['Id']
#del datrain['Y4s1_grade']
#del datrain['Y4s1_complete']
#del datrain['Y4s1_enrol']

x_train = datrain.drop('Failure', axis=1).values
y_train = datrain['Failure'].values



#model = LogisticRegression(solver='lbfgs', max_iter=500)
#model.fit(x_train, y_train)

#model = KNeighborsClassifier(n_neighbors=5)
#model.fit(x_train,y_train)

model = RidgeClassifier()
model.fit(x_train,y_train)



predicted = model.predict(test)

dffail= pd.DataFrame({'Failure': predicted})

frames = (dfid, dffail)

datasubm = pd.concat(frames, axis=1)

datasubm.to_csv(r'SubmissionRidge.csv', index = False)

print(datasubm)






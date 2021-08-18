import numpy as np
from sklearn.model_selection import train_test_split

class Node:

    def __init__(self, label, parent, attvalue_link=None): #o node tem a sua label, seu parente e um array dos seus child.
        self.label = label                                 #o attvalue_link e a ligacao do valor que liga o child e o parent
        self.parent = parent                               #por exemplo do atributo outlook. sunny seria um attvalue_link
        self.child = []
        self.attvalue_link = attvalue_link

    def set_child(self, child):
        self.child.append(child)

    def get_child(self, attvalue_link):
        for child in self.child:
            if child.attvalue_link == attvalue_link:
                return child
        return None

class DecisionTreeREPrune:

    def __init__(self, criterion="entropy", prune=False):
        self.root = None
        self.attributes = None

    def create_node(self,label, parent, attvalue_link=None):
        return Node(label, parent, attvalue_link)


    def entropy(self, data):    
        count = self.count_unique(data)

        entropy = 0

        total = len(data)

        for classe in count:
            p = count[classe] / total
            entropy += (-p) * np.log2(p)

        return entropy

    def fit(self, datax, datay): 

        self.attributes = [str(x) for x in range(datax.shape[1])]

        if np.all(datay == datay[0]):
            self.root = self.create_node(datay[0], None)

        #gerar a arvore
        else:
            att = self.bestatt(datax, datay, [])
            root_label = self.attributes[att]
            self.root = self.create_node(root_label, None)
            self.buildtree(datax, datay, att, [att], self.root)

    def info_gain(self, att, datay):
        attribute_entropy = 0
        for valor in np.unique(att):
            y = datay[att == valor]
            fraction = y.size/datay.size
            attribute_entropy += fraction * self.entropy(y)
        return self.entropy(datay) - attribute_entropy


    def count_unique(self, data): #funcao que devolve a contagem de cada valor encontrada num atributo
        counts = {}
        for label in data:
            if label not in counts:
                counts[label] = 1
            else:
                counts[label] += 1

        return counts

    def buildtree(self, datax, datay, att, previous, current):

        attribute_column = datax[:, att] #escolher uma coluna 

        uniques_values = np.unique(attribute_column).tolist()
        for valor in uniques_values:
            x = datax[attribute_column == valor]
            y = datay[attribute_column == valor]

            if np.all(y == y[0]):
                child = self.create_node(y[0], current, valor)
                current.set_child(child)

            # aqui procuramos o melhor att
            else:
                child = self.bestatt(x, y, previous)
                child_label = self.attributes[child]
                child_node = self.create_node(child_label, current, valor)
                current.set_child(child_node)
                self.buildtree(x, y, child, previous + [child], child_node)

    def bestatt(self, datax, datay, previous): #escolha do melhor abributo (com maior info gain)
        gain = []
        for i, att in enumerate(datax.T):
            if i not in previous:
                gain.append(self.info_gain(att, datay)) #criamos um array com o info gain
            else:
                gain.append(float("-inf"))
        return gain.index(max(gain)) #retiramos o index do maior info-gain

    def predict(self, datax):
        datay = np.empty(datax.shape[0], dtype="<U10")
        for i, row in enumerate(datax):
            datay[i] = self.predict_row(row)
        return datay

    def predict_row(self, row):
        current_node = self.root
        while current_node.child:
            attribute = current_node.label
            attribute_column = self.attributes.index(attribute)
            attribute_value = row[attribute_column]
            current_node = current_node.get_child(attribute_value)

        return current_node.label

    def score(self, datax, datay):
        predi = self.predict(datax)
        return (sum(datay == predi) / datax.shape[0])

import numpy as np
from sklearn.model_selection import train_test_split

data = np.genfromtxt('data/contact-lenses.csv', delimiter=",", dtype="str")

datax = data[:, :-1]
datay = data[:, -1]


x_train, x_test, y_train, y_test = train_test_split(datax, datay, random_state=0)

classifier = DecisionTreeREPrune()
classifier.fit(x_train, y_train)
result = classifier.score(x_test, y_test)
print("Percentagem de casos corretamente classificados {:2.2%}".format(result))



   



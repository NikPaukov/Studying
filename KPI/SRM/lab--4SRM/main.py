import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# import xlrd
# pd.ExcelFile
# C:\Users\User\Desktop\
data = pd.read_excel(r'C:\Users\Elfar\Desktop\sr5.1.xlsx')
df = pd.DataFrame(data,  columns=['Unnamed: 4', 'Unnamed: 1'])
x = df[2:9]
x.plot(x='Unnamed: 1', y='Unnamed: 4')
plt.show()
#df1 = pd.DataFrame(data, columns=['Unnamed: 1'])
#y = df1[2:9]


# print(x)
# print(y)
#
# t = np.arange(0, len(x), 1)
# fig, ax = plt.subplots()
# print(t)
# plt.figure(1)
# plt.plot(t, x.astype(dtype=float), 'r')
# plt.grid(True)
# plt.show()

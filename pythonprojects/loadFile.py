import sys
import pandas
from pyspark.sql import SparkSession
from pyspark.ml.evaluation import BinaryClassificationEvaluator
from pyspark.ml.classification import LogisticRegression
from pyspark.sql import Row
from pyspark.sql.types import *
from pyspark.sql.functions import UserDefinedFunction
from pyspark.ml.linalg import Vectors

print("STARTING HOUSEHOLD PREDICTION SIMULATOR:")
print(" ")

# Build the SparkSession
spark = SparkSession.builder.master("local") \
   .appName("household Chances Model") \
   .config("spark.executor.memory", "2gb") \
   .getOrCreate()
   
sc = spark.sparkContext

# Create data frames for the train data
df = pandas.read_excel('C:/pythonprojects/household.xlsx', sheet_name='Grocery Data',inferSchema='')
sdf = spark.createDataFrame(df)
#sdf.printSchema()


from pyspark.ml.feature import VectorAssembler
vectorAssembler = VectorAssembler(inputCols = ['N_Adults', 'Family_Income', 'N_Vehicles', 'Distance_to_Store', 'Vegetarian','N_Children'], outputCol = 'features')
vhouse_df = vectorAssembler.transform(sdf)
vhouse_df = vhouse_df.select(['features', 'Grocery_Bill'])
#vhouse_df.show(3)


splits = vhouse_df.randomSplit([0.7, 0.3])
train_df = splits[0]
test_df = splits[1]

from pyspark.ml.regression import LinearRegression
lr = LinearRegression(featuresCol = 'features', labelCol='Grocery_Bill', maxIter=10, regParam=0.3, elasticNetParam=0.8)
lr_model = lr.fit(train_df)
print("Coefficients: " + str(lr_model.coefficients))
print("Intercept: " + str(lr_model.intercept))

trainingSummary = lr_model.summary
print("RMSE: %f" % trainingSummary.rootMeanSquaredError)
print("r2: %f" % trainingSummary.r2)

lr_predictions = lr_model.transform(test_df)
lr_predictions.select("prediction","features").show(5)
lr_model.write().overwrite().save("C:\pythonprojects\lr_model")

#============================================================================

# dataframe for user input file
#predictorDf = pandas.read_excel('C:/pythonprojects/user-input.xlsx', sheet_name='Grocery Data',inferSchema='')
#predictorSdf = spark.createDataFrame(predictorDf)
#predictorSdf.printSchema()

#vectorAssembler2 = VectorAssembler(inputCols = ['N_Adults', 'Family_Income', 'N_Vehicles', 'Distance_to_Store', 'Vegetarian','N_Children'], outputCol = 'features')
#predictor_house_df = vectorAssembler2.transform(predictorSdf)
#predictor_house_df = predictor_house_df.select(['features'])
#predictor_house_df.show()

#from pyspark.ml.regression import LinearRegressionModel
#lr_model2 = LinearRegressionModel.load("C:\pythonprojects\lr_model")

#predictions = lr_model2.transform(predictor_house_df)
#predictions.select("prediction","features").show()

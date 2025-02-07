import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.sql.SparkSession

object FraudDetection {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("FraudDetection").getOrCreate()
    
    // Load data
    val df = spark.read.option("header", "true").csv("s3://your-bucket/transactions.csv")
    
    // Train model
    val gbt = new GBTClassifier().setLabelCol("label").setFeaturesCol("features")
    val model = gbt.fit(df)
    
    // Save model
    model.write.overwrite().save("s3://your-bucket/fraud_model")
  }
}

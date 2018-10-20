package com.testapp.recommender;

import java.io.IOException;

import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.DataModel;
import net.librec.data.model.TextDataModel;
import net.librec.job.RecommenderJob;
import net.librec.math.algorithm.Randoms;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.UserKNNRecommender;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

@SuppressWarnings("unused")
public class Driver {
	public static void main(String[] args) throws LibrecException, ClassNotFoundException, IOException
	{
		//Step 1: Make a configuration file and configure your recommender.
		Configuration conf=getConfiguration();
		RecommenderJob job=new RecommenderJob(conf);
		job.runJob();
		//Step 2- Build a Data Model which will prepare your data.
//		DataModel model=new TextDataModel(conf);
//		model.buildDataModel();
//		//Step 3- Build a Similarity Matrix.
//		RecommenderSimilarity similarity=new PCCSimilarity();
//		similarity.buildSimilarityMatrix(model);
//		//Step 4- Build a Recommender Object which will actually recommend items.
//		Recommender r=new UserKNNRecommender();
//		//Step 5- Perform Recommendation Step.
//		r.recommend(new RecommenderContext(conf,model,similarity));
	}
	static Configuration getConfiguration()
	{
		Configuration conf=new Configuration();
		conf.set("dfs.data.dir","./data");
		conf.set("dfs.result.dir","./result");
		conf.set("data.input.path","movielens/ml-100k");
		conf.set("data.column.format", "UIRT");
		conf.set("data.model.splitter","ratio");
		conf.set("data.splitter.ratio","user");
		conf.set("data.model.format","text");
		conf.set("data.splitter.trainset.ratio","0.8");
		conf.set("rec.eval.enable","true");
		conf.set("rec.recommender.similarities","user");
		conf.set("rec.recommender.class", "net.librec.recommender.cf.UserKNNRecommender");
		conf.set("rec.neighbors.knn.number","25");
		conf.set("rec.similarity.class","com.testapp.similarity.JMSDSimilarity");
		//conf.set("rec.similarity.class","net.librec.similarity.PCCSimilarity");
		conf.set("rec.recommender.isranking","false");
		conf.set("rec.filter.class","generic");
		conf.set("rec.eval.classes","net.librec.eval.rating.MAEEvaluator");
		conf.set("rec.recommender.verbose","true");
		return conf;
	}
}

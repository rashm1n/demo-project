//package it.codegen.rnd.chatbots.master.service;
//
//import it.codegen.rnd.logging.SystemLogger;
//import it.codegen.rnd.sense.SenseNLPEngine;
//import it.codegen.rnd.sense.core.Pipeline;
//import it.codegen.rnd.sense.core.impl.SimplePipeline;
//import it.codegen.rnd.sense.core.nlp.tool.simple.SimpleTokenizer;
//import it.codegen.rnd.sense.exception.SenseException;
//
//import java.util.Properties;
//
//public class SenseService
//{
//
//	private static SenseNLPEngine senseEngine;
//	private static Pipeline pipeline;
//	private static final String LOG_TOKEN = "IBO_TAG";
//
//	static
//	{
//		try
//		{
//			senseEngine = new SenseNLPEngine( new Properties() );
//
//			senseEngine.init();
//
//			pipeline = senseEngine.createPipeline( SimplePipeline.KEY
//					, new SimpleTokenizer()
//			);
//			pipeline.init();
//		}
//		catch ( SenseException e )
//		{
//			SystemLogger.logErrorMessege( LOG_TOKEN, e );
//		}
//	}
//
//	public static SimplePipeline
//
//}

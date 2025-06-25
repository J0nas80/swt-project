package de.fh_dortmund.swt2.fake_service.service;

import org.springframework.stereotype.Service;

@Service
public class RandomizerService {
	
	public int getRandomInt(int max) throws Exception
	{
		return (int) getRandomDouble(max);		
	}

	public int getRandomInt(int min, int max) throws Exception
	{
		return (int) getRandomDouble(min, max);		
	}

	public double getRandomDouble(double max) throws Exception
	{
		if(max < 0)
			throw new Exception("Nur positive zahlen sind erlaubt");
		return (Math.random() * max);

	}

	public double getRandomDouble(double min, double max) throws Exception
	{
		if(max <= min)
		{
			throw new Exception("max muss größer als min sein");
		}

		return (int) ((Math.random()* (max-min))+min);
	}	
}

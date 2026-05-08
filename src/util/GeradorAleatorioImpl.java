package util;

import java.util.Random;

/**
 * Classe que implementa GeradorAleatorio para gerar números aleatórios usando o Random.
 */
public class GeradorAleatorioImpl implements GeradorAleatorio {
	
	private Random random = new Random();
	
	@Override
	public int sortearNumeroInteiro(int min, int max) {
		 
		return min + random.nextInt(max - min + 1);
	}

}

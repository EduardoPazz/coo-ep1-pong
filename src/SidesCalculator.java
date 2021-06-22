public class SidesCalculator {
    
	/**
	 * Método que calcula o lado esquerdo de um retângulo
	 * @param rectangle um objeto que implementa a interface Rectangle
	 * @return um double representando o lado esquerdo do objeto no eixo x
	 */
    public static double getLeftSide(Rectangle rectangle) {
		return rectangle.getCx() - rectangle.getWidth() / 2;
	}
    
	/**
	 * Método que calcula o lado direito de um retângulo
	 * @param rectangle um objeto que implementa a interface Rectangle
	 * @return um double representando o lado direito do objeto no eixo x
	 */
    public static double getRightSide(Rectangle rectangle) {
		return rectangle.getCx() + rectangle.getWidth() / 2;
	}

	/**
	 * Método que calcula o lado do topo de um retângulo
	 * @param rectangle um objeto que implementa a interface Rectangle
	 * @return um double representando o lado do topo do objeto no eixo y
	 */
    public static double getTopSide(Rectangle rectangle) {
		return rectangle.getCy() - rectangle.getHeigth() / 2;
	}


	/**
	 * Método que calcula o lado de baixo de um retângulo
	 * @param rectangle um objeto que implementa a interface Rectangle
	 * @return um double representando o lado dw baixo do objeto no eixo y
	 */
    public static double getBottomSide(Rectangle rectangle) {
		return rectangle.getCy() + rectangle.getHeigth() / 2;
	}
}

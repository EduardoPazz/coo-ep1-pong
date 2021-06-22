/**
 * Interface implementada por métodos que possuem coordenadas de
 * centro (cx e cy), altura e largura 
 */
public interface Rectangle {

    /**
     * Getter que retorna a coordenada central no eixo x
     * @return um double representando a coordenada
     */
    double getCx();

    /**
     * Getter que retorna a coordenada central no eixo y
     * @return um double representando a coordenada
     */
    double getCy();


    /**
     * Getter que retorna a altura em pixels
     * @return um double representando a altura
     */
    double getHeigth();


    /**
     * Getter que retorna o comprimento em pixel
     * @return um double representando o comprimento
     */
    double getWidth();
}

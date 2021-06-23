import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	private int score;
	private String playerId;

	/**
		Construtor da classe Score.
		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	public Score(String playerId){
		this.playerId = playerId;
	}

	/**
		Método de desenho do placar.
	*/
	public void draw() {

		/**
		 * Como não dá para alterar o uso do construtor do Player para adicionar
		 * um atributo "ALIGN", é necessário fazer essa gambiarra aqui que utiliza
		 * o fato do Player 1 ser sempre a esquerda o Player 2 sempre à direita
		 */
		int align;

		if (this.playerId == "Player 1") {
			GameLib.setColor(Color.GREEN);
			align = GameLib.ALIGN_LEFT;
		} else {
			GameLib.setColor(Color.BLUE);
			align = GameLib.ALIGN_RIGHT;
		}
					
		GameLib.drawText(this.playerId + ": " + this.score, 70, align);
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/
	public void inc(){
		this.score++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.
		@return o valor inteiro referente ao total de pontos.
	*/
	public int getScore(){
		return this.score;
	}
}

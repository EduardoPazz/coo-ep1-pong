import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;
	private double angle;

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/
	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		// this.speed = .1;
		this.angle = this.getRandomAngle();
	}

	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/
	public void draw(){
		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void update(long delta){
		double distanceTravelled = delta * this.speed;

		this.cx += distanceTravelled * Math.cos(angle);
		this.cy -= distanceTravelled * Math.sin(angle);
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/
	public void onPlayerCollision(String playerId){		
		this.angle = Math.PI - this.angle;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/
	public void onWallCollision(String wallId){
		this.angle = ( wallId == "Left" || wallId == "Right" )
			? ( Math.PI - this.angle )
			: ( 2 * Math.PI - this.angle );
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	public boolean checkCollision(Wall wall) {
		boolean hadCollision;

		switch (wall.getId()) {
			case "Left":
				double rightWallSide = wall.getCx() + wall.getWidth() / 2;
				hadCollision = this.getLeftSide() <= rightWallSide;
				break;
		
			case "Right":
				double leftWallSide = wall.getCx() - wall.getWidth() / 2;
				hadCollision = this.getRightSide() >= leftWallSide;
				break;

			case "Top":
				double bottomWallSide = wall.getCy() + wall.getHeight() / 2;
				hadCollision = this.getTopSide() <= bottomWallSide;
				break;

			default: // Bottom
				double topWallSide = wall.getCy() - wall.getHeight() / 2;
				hadCollision = this.getBottomSide() >= topWallSide;
				break;
		}

		return hadCollision;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	
	public boolean checkCollision(Player player){

		double playerTopSide = player.getTopSide();
		double playerLeftSide = player.getLeftSide();
		double playerRightSide = player.getRightSide();
		double playerBottomSide = player.getBottomSide();

		double ballBottomSide = this.getBottomSide();
		double ballRightSide = this.getRightSide();
		double ballLeftSide = this.getLeftSide();
		double ballTopSide = this.getTopSide();


		boolean isBetweenTopAndBottom = (ballTopSide >= playerTopSide)
			&& (ballBottomSide <= playerBottomSide);

		boolean hadCollision = isBetweenTopAndBottom
			&& (ballLeftSide <= playerRightSide)
			&& (ballRightSide >= playerLeftSide);
			

		System.out.println("Player Colision:" + hadCollision);
		return hadCollision;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/
	public double getCy(){
		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/
	public double getSpeed(){
		return this.speed;
	}

	private double getRandomAngle() {
		double randomAngle;

		/**
		 * Esse do...while evita que o ângulo que a bola assume seja
		 * muito vertical ou muito horizontal
		 */
		do randomAngle = (Math.random() * 2 * Math.PI); while (
			// Evita que a bola aponte muito para cima
			(randomAngle > Math.PI / 3) && (randomAngle < 2 * Math.PI / 3)

			||

			// Evita que a bola aponte muito para baixo
			(randomAngle > 4 * Math.PI / 3) && (randomAngle < 5 * Math.PI / 3)

			||

			// Evita que a bola aponte muito para esquerda
			(randomAngle > 5 * Math.PI / 6) && (randomAngle < 6 * Math.PI / 6)

			||

			// Evita que a bola aponte muito para direita
			(randomAngle > 11 * Math.PI / 6) && (randomAngle < Math.PI / 6)

		);

		return randomAngle;
	}

	private double getLeftSide() {
		return this.cx - this.width / 2;
	}

	private double getRightSide() {
		return this.cx + this.width / 2;
	}

	private double getTopSide() {
		return this.cy - this.height / 2;
	}

	private double getBottomSide() {
		return this.cy + this.height / 2;
	}
}
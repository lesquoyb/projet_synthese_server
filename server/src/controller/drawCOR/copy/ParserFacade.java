package controller.drawCOR.copy;

import view.interfaces.DrawingAreaInt;

/**
 * Cette classe est une facade du Design Pattern Chain Of Responsibility 
 * permettant de parser une chaine et d'afficher l'image correspondente.
 * @author baptiste
 *
 */
public class ParserFacade {
	
	ExpertParser first;
	
	public ParserFacade(){
		
		first = new ExpertParserSegment();
		first.ajouterSuivant(new ExpertParserCercle());
		first.ajouterSuivant(new ExpertParserPolygone());
		first.ajouterSuivant(new ExpertParserTriangle());
		
	}
	
	/**
	 * La méthode à appeler pour lancer la façade.
	 * @param toParse
	 * @param toDraw
	 * @return
	 */
	public boolean draw(String toParse, DrawingAreaInt toDraw){
		return first.draw(toParse, toDraw);
	}

}

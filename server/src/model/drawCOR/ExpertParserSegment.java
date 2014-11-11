package model.drawCOR;

import view.interfaces.DrawingAreaInt;
/**
 * Permet de parser et d'afficher une chaine représentant un segment.
 * Le protocole choisi veut qu'un segment soit représenté ainsi:
 * "segment: couleur , x1, y1, x2, y2"
 * où couleur est en hexa et les points sont des entiers
 * 
 * @author baptiste
 *
 */
public class ExpertParserSegment extends ExpertParser{

	@Override
	public boolean draw1(String toParse, DrawingAreaInt toDraw) {
		String type = "segment:";
		if(toParse.startsWith(type)){
			String content[] = toParse.substring(type.length()).split(",");
			if(content.length == 5){
				String couleur = content[0].trim();
				int x1 =  Integer.parseInt(content[1].trim());
				int y1 =  Integer.parseInt(content[2].trim());
				int x2 =  Integer.parseInt(content[3].trim());
				int y2 =  Integer.parseInt(content[4].trim());
				toDraw.drawLine(couleur, x1, y1, x2, y2);
				return true;
			}
		}
		return false;
	}
	
	
	
	

}

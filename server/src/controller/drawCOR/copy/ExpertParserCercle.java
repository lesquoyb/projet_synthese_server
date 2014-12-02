package controller.drawCOR.copy;

import view.interfaces.DrawingAreaInt;


/**
 * L'expert parser de cercle.
 * @author baptiste
 *
 */
public class ExpertParserCercle extends ExpertParser {

	@Override
	public boolean draw1(String toParse, DrawingAreaInt toDraw) {		
		String type = "cercle:";
		if(toParse.startsWith(type)){
			String content[] = toParse.substring(type.length()).split(",");
			if(content.length == 4){
				String couleur = content[0].trim();
				int x1 = (int) Double.parseDouble(content[1].trim());
				int y1 =  (int) Double.parseDouble(content[2].trim());
				int radius =  (int) Double.parseDouble(content[3].trim());
				toDraw.drawEllipse(couleur, x1, y1, radius);
				return true;
			}
		}
		return false;
	}

}

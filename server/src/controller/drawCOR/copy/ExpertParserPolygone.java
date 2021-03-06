package controller.drawCOR.copy;

import java.util.ArrayList;

import view.interfaces.DrawingAreaInt;

/**
 * L'expert parser de polygone.
 * @author baptiste
 *
 */
public class ExpertParserPolygone extends ExpertParser {

	@Override
	public boolean draw1(String toParse, DrawingAreaInt toDraw) {
		String type = "polygone:";
		if(toParse.startsWith(type)){
			String content[] = toParse.substring(type.length()).split(",");
			if ( (content.length >= 7) && ( (content.length % 2) == 1 ) ){
				String couleur = content[0].trim();
				int x[] = new int[(content.length/2)];
				int y[] = new int[(content.length/2)];
				
				for (int i = 1 ; i < content.length ; i+=2){
					int index = i/2;
					x[index] = (int) Double.parseDouble(content[i].trim());
					y[index] = (int) Double.parseDouble(content[i+1].trim());
				}
				toDraw.drawPolygon(couleur, x, y, content.length/2 );
				return true;
			}
		}
		return false;
	}
	
	

}

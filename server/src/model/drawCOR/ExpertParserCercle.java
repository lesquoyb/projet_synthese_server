package model.drawCOR;

import view.interfaces.DrawingAreaInt;

public class ExpertParserCercle extends ExpertParser {

	@Override
	public boolean draw1(String toParse, DrawingAreaInt toDraw) {		
		String type = "cercle:";
		if(toParse.startsWith(type)){
			String content[] = toParse.substring(type.length()).split(",");
			if(content.length == 4){
				String couleur = content[0].trim();
				int x1 =  Integer.parseInt(content[1].trim());
				int y1 =  Integer.parseInt(content[2].trim());
				int radius =  Integer.parseInt(content[3].trim());
				toDraw.drawEllipse(couleur, x1, y1, radius);
				return true;
			}
		}
		return false;
	}

}
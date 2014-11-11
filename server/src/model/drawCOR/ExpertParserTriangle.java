package model.drawCOR;

import view.interfaces.DrawingAreaInt;

public class ExpertParserTriangle extends ExpertParser {

	@Override
	public boolean draw1(String toParse, DrawingAreaInt toDraw) {
		
		String type = "triangle:";
		if(toParse.startsWith(type)){
			String content[] = toParse.substring(type.length()).split(",");
			if(content.length == 7){
				String couleur = content[0].trim();
				int x1 =  Integer.parseInt(content[1].trim());
				int y1 =  Integer.parseInt(content[2].trim());
				int x2 =  Integer.parseInt(content[3].trim());
				int y2 =  Integer.parseInt(content[4].trim());
				int x3 =  Integer.parseInt(content[5].trim());
				int y3 =  Integer.parseInt(content[6].trim());
				int x[] = {x1, x2, x3};
				int y[] = {y1, y2, y3};
				toDraw.drawPolygon(couleur, x , y, 3 );
				return true;
			}
		}
		return false;
	}
}

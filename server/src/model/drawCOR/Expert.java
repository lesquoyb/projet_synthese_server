package model.drawCOR;

import view.DrawingArea;
import view.interfaces.DrawingAreaInt;

public abstract class Expert {
	
	/**
	 * La méthode utilisé par tous expert pour essayer de résoudre le problème
	 * @param toParse la chaîne à parser
	 * @param toDraw la zone sur laquelle il faut dessiner
	 * @return si tous c'est bien passé elle retourne true, si on n'a pas trouvé d'expert pour parser on retourne false
	 */
	public abstract boolean draw(String toParse, DrawingAreaInt toDraw);
	
}

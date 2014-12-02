package controller.drawCOR.copy;

import view.interfaces.DrawingAreaInt;

/**
 * Classe abstraite définissant le comportement d'un expert parser (c'est à dire qui analyse du texte).
 * Defini l'objet {@link ExpertParser} suivant, pour le DP COR.
 * @author baptiste
 *
 */
public abstract class ExpertParser extends Expert {

	private ExpertParser suivant;

	public ExpertParser() {
		suivant = null;
	}
	@Override
	public boolean draw(String toParse,DrawingAreaInt toDraw){
		if( draw1(toParse,toDraw) == false){
			if(suivant != null){
				return suivant.draw(toParse,toDraw);
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * La méthode à implémenter par tous les {@link ExpertParser} dans {@link Expert.draw()}
	 * @param toParse la chaîne à parser
	 * @param toDraw la zone sur laquelle il faut dessiner la chaîne parsée
	 * @return true si la méthode a fonctionné, false pour passer à l'expert suivant
	 */
	public abstract boolean draw1(String toParse, DrawingAreaInt toDraw);
	
	/**
	 * met à jour la valeur du suivant dans la chaine de responsabilité
	 * @param ex l'expert suivant dans la chaine
	 */
	public void ajouterSuivant(ExpertParser ex){
		if(suivant != null){
			suivant.ajouterSuivant(ex);
		}
		else{
			suivant = ex;	
		}
		
	}
}

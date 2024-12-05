import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.SimulationConstants;

public class arbrerecouvrant extends LC1_Algorithm {

    @Override
    public Object clone() {
        return new arbrerecouvrant();
    }

    @Override
    public String getDescription() {
        return "Calcul de l'arbre recouvrant avec la synchronisation locale de type étoile ouverte.";
    }

    @Override
    protected void beforeStart() {
        if (isCenter()) {
            // Initialisation du centre de l'étoile
            setLocalProperty("label", "A");
            putProperty("Affichage", "A", SimulationConstants.PropertyStatus.DISPLAYED);
        } else {
            // Initialisation des autres nœuds
            setLocalProperty("label", "N");
            putProperty("Affichage", "N", SimulationConstants.PropertyStatus.DISPLAYED);
        }
    }

    @Override
    protected void onStarCenter() {
        
        if (getLocalProperty("label").equals("N") && getNeighborProperty(0, "label").equals("A")) {
           
            setDoorState(new MarkedState(true), 0); 
           
            setLocalProperty("label", "A");
            putProperty("Affichage", "A", SimulationConstants.PropertyStatus.DISPLAYED);
        }
    }

    private boolean isCenter() {
        return vertex.getId() == 0;
    }
}

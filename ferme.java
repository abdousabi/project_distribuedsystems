import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.SimulationConstants;

public class ferme extends LC2_Algorithm {

    // Le centre de l'étoile est initialisé à 'true' uniquement pour le nœud avec l'ID 0.
    private boolean isCenter = false;

    @Override
    public Object clone() {
        return new ferme();
    }

    @Override
    public String getDescription() {
        return "Calcul de l'arbre recouvrant avec la synchronisation locale de type étoile fermée.";
    }

    @Override
    protected void beforeStart() {
        // Tous les nœuds commencent par l'état "N".
        setLocalProperty("label", "N");
        putProperty("Affichage", "N", SimulationConstants.PropertyStatus.DISPLAYED);

        // Identifier le centre de l'étoile.
        isCenter = vertex.getId() == 0;

        // Seul le centre de l'étoile est marqué "A" initialement.
        if (isCenter) {
            setLocalProperty("label", "A");
            putProperty("Affichage", "A", SimulationConstants.PropertyStatus.DISPLAYED);
        }
    }
    @Override
    protected void onStarCenter() {
        // Si ce nœud est actif, il va activer un de ses voisins non actifs.
        if (getLocalProperty("label").equals("A")) {
            for (int i = 0; i < getArity(); i++) {
                if (getNeighborProperty(i, "label").equals("N")) {
                    // Marquer l'arête et changer l'état du voisin à "A".
                    setDoorState(new MarkedState(true), i);
                    setNeighborProperty(i, "label", "A");
                    // Une fois un voisin marqué, propager l'état "A" en changeant également l'état de ce nœud.
                    setLocalProperty("label", "F"); // F pour fini dans ce contexte.
                }
            }
        }
    }
}

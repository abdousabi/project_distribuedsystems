import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.SimulationConstants;

public class Election extends LC0_Algorithm {

    int porte_origin = -1;
    String neighborstate[];

    @Override
    public String getDescription() {
        return "Leader Election Algorithm using handshakes .\n";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("nbrvois", vertex.degree());

        etatVoisins = new String[getArity()];
        for (int i = 0; i < getArity(); i++) {
            neighborstate[i] = "N";
        }
        putProperty("Affichage",Integer.parseInt(getLocalProperty("nbrneighbor").toString()),SimulationConstants.PropertyStatus.DISPLAYED);

    }

    @Override
    protected void onStarCenter() {
        etatVoisins[neighborDoor] = getNeighborProperty("label").toString();

        // rule 1
        if (getLocalProperty("label").equals("N") &&
                getNeighborProperty("label").equals("N") &&
                Integer.parseInt(getLocalProperty("nbrneignbor").toString()) == 1 &&
                Integer.parseInt(getNeighborProperty("nbrneignbor").toString()) > 1) {

            setLocalProperty("label", "F");
            setLocalProperty("nbrneignbor", Integer.parseInt(getLocalProperty("nbrneignbor").toString()) - 1);
            setNeighborProperty("nbrneignbor", Integer.parseInt(getNeighborProperty("nbrneignbor").toString()) - 1);
        }
        // rule 2
        else if (getLocalProperty("label").equals("N") &&
                getNeighborProperty("label").equals("N") &&
                Integer.parseInt(getLocalProperty("nbrneignbor").toString()) == 1 &&
                Integer.parseInt(getNeighborProperty("nbrneignbor").toString()) == 1) {

            setLocalProperty("label", "E");
            setNeighborProperty("label", "F");
            setLocalProperty("nbrneignbor", Integer.parseInt(getLocalProperty("nbrneignbor").toString()) - 1);
            setNeighborProperty("nbrneignbor", Integer.parseInt(getNeighborProperty("nbrneignbor").toString()) - 1);

        }

    }

    private int nbVoisinsN() {
        int nb = 0;
        for (int i = 0; i < neighborstate.length; i++) {
            if (etatVoisins[i].equals("N")) {
                nb++;
            }
        }
        return nb;
    }

    @Override
    public Object clone() {
        return new Election();
    }
}
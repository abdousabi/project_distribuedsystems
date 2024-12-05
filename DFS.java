import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
//Regles de Reecritufre:
//R1: N --- A ----> A ___ M
//R2: A(x) ___ M(y) ----> F(x) ___ A(x+y)  (Pas de Voisin N)
public class DFS extends LC0_Algorithm{
    int fatherDoor=-1;
    String[] etatsVoisins;
    Boolean Transform;
    @Override
    public Object clone() {
        return new DFS();
    }

    @Override
    public String getDescription() {
        return "TP1 Spanning tree algorithm using LCO.\n"+"Rule : A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("counter",1);
        setLocalProperty("label",vertex.getLabel());
        putProperty("Affichage",getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
        etatsVoisins = new String[getArity()];
        for(int i=0;i<getArity();i++){
            etatsVoisins[i] = "N";
        }
    }

    @Override
    protected void onStarCenter() {
        etatsVoisins[neighborDoor] = getNeighborProperty("label").toString();
        if(getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("A")){
            setLocalProperty("label","A");
            setNeighborProperty("label","M");
            setDoorState(new MarkedState(true),neighborDoor);
            fatherDoor = neighborDoor;
        }else if(getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("M") && neighborDoor == fatherDoor){
            for(int i=0;i<getArity();i++){
                if(etatsVoisins[i].equals("N")){
                    Transform = false;
                    break;
                }else{
                    Transform = true;
                }
            }
            if(Transform) {
                setLocalProperty("label", "F");
                setNeighborProperty("label", "A");
                setNeighborProperty("counter",Integer.parseInt(getLocalProperty("counter").toString())+Integer.parseInt(getNeighborProperty("counter").toString()));
            }
        }
        putProperty("Affichage",getLocalProperty("counter").toString(), SimulationConstants.PropertyStatus.DISPLAYED);
    }
}

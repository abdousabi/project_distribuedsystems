import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class EtoileOuverteArbreRecouvrant extends LC1_Algorithm{
        
        @Override
        public String getDescription() {

                return "Spanning tree algorithm using LC1 étoile ouverte.\n" +
                                "Rule 1: N---A ---> A--x--A \n";
        }

        @Override
        protected void beforeStart(){
                setLocalProperty("label", vertex.getLabel());

        }

        @Override
        protected void onStarCenter() {

                for(int i=0; i < getActiveDoors().size(); i++){
                        int numPort = getActiveDoors().get(i);
                        System.out.println("L'etat du voisin connecte sur le port " + numPort + " est : " + getNeighborProperty(numPort, "label"));
                        if(getLocalProperty("label").equals("N") &&
                                getNeighborProperty(numPort, "label").equals("A")){

                                setLocalProperty("label", "A");
                                setDoorState(new MarkedState(true), numPort);
                                System.out.println("--------------------------------------");
                                break;
                        }
                }
        }

        @Override
        public Object clone() {
                return new EtoileOuverteArbreRecouvrant();
        }
}
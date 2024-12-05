import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class EtoileFermeArbreRecouvrant extends LC2_Algorithm{
        
        @Override
        public String getDescription() {

                return "Spanning tree algorithm using LC2 étoile ferme.\n" +
                                "Rule 1: A---N ---> A--x--A";
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
                        if(getLocalProperty("label").equals("A") &&
                                getNeighborProperty(numPort, "label").equals("N")){

                                setNeighborProperty(numPort, "label", "A");
                                setDoorState(new MarkedState(true), numPort);
                        }
                }
        }

        @Override
        public Object clone() {
                return new EtoileFermeArbreRecouvrant();
        }
}
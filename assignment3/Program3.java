package assignment3;
/*
 * Name: Christian Han
 * EID: cjh3752
 */

import java.util.ArrayList;


/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program3 extends AbstractProgram3 {
    /**
     * Determines a solution to the optimal antenna range for the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the optimal solution
     */
    @Override
    public TownPlan OptimalRange(TownPlan town) {

        int numHouses = town.getHouseCount();
        int numStations = town.getStationCount();

        ArrayList<Float> housePositions = town.getHousePosition();
        float[][] optAntennaRange = new float[numStations][numHouses]; //numStation rows , numHouses colums


        /* base case */
        for(int i = 0; i < numHouses; i++){
            float range = (housePositions.get(i) - housePositions.get(0))/2;
            optAntennaRange[0][i] = range;
        }

        float currentRange;
        float previousRange;
        ArrayList<Float> maximumValues = new ArrayList<Float>();

        for(int i = 1; i < numStations; i++){
            for(int j = 1; j < numHouses; j++){
                for(int k = 0; k < j; k++){
                    currentRange = (housePositions.get(j) - housePositions.get(j-k))/2;
                    previousRange = optAntennaRange[i-1][j-k-1];
                    if(currentRange > previousRange){
                        maximumValues.add(currentRange);
                    }else{
                        maximumValues.add(previousRange);
                    }
                }
                float min = maximumValues.get(0);
                for (float value : maximumValues){
                    min = min < value ? min : value;
                }
                optAntennaRange[i][j] = min;
                maximumValues.clear();
            }
        }

        town.setRange(optAntennaRange[numStations-1][numHouses-1]);
        return town;
    }

    /**
     * Determines a solution to the set of base station positions that optimise antenna range for the given input set. Study the
     * project description to understand the variables which } represent the input to your solution.
     *
     * @return Updaed TownPlan town with the optimal solution
     */
    @Override
    public TownPlan OptimalPosBaseStations(TownPlan town) {

        int numHouses = town.getHouseCount();
        int numStations = town.getStationCount();

        ArrayList<Float> housePositions = town.getHousePosition();
        ArrayList<Float> stationPositions = town.getStationPosition();

        float[][] optAntennaRange = new float[numStations][numHouses]; //numStation rows , numHouses colums
        ArrayList<Float>[][] optStationPosition = new ArrayList[numStations][numHouses];


        for(int i = 0; i < numStations; i++){
            for(int j = 0; j < numHouses; j++){
                optStationPosition[i][j] = new ArrayList<Float>();
            }
        }

        /* base case */
        for(int i = 0; i < numHouses; i++){
            float range = (housePositions.get(i) - housePositions.get(0))/2;
            optAntennaRange[0][i] = range;
            float firstHouse = housePositions.get(0) + range;
            optStationPosition[0][i].add(firstHouse);
        }

        float currentRange;
        float previousRange;
        ArrayList<Float> maximumValues = new ArrayList<Float>();
        int index = 0;
        int min;


        for(int i = 1; i < numStations; i++){
            for(int j = 1; j < numHouses; j++){

                float opt_range = 0;
                int opt_position = 0;

                for(int k = 0; k < j; k++){
                    currentRange = (housePositions.get(j) - housePositions.get(j-k))/2;
                    previousRange = optAntennaRange[i-1][j-k-1];

                    if(currentRange < opt_range && previousRange > opt_position){
                        opt_range = currentRange;
                        opt_position = k;
                    }

                }


                optAntennaRange[i][j] = opt_range;
                //optStationPosition[i][j].add(optStationPosition[i][opt_position]);
                maximumValues.clear();
            }
        }

        town.setPositionBaseStations(optStationPosition[numStations-1][numHouses-1]);
        return null;
    }
}

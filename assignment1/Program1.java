package assignment1;

/*
 * Name: Christian Han
 * EID: cjh3752
 */

import com.sun.xml.internal.bind.v2.TODO;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the Stable Marriage problem.
     * Study the description of a Matching in the project documentation to help you with this.
     */
    @Override
    public boolean isStableMatching(Matching marriage) {

        Matching match = marriage;

        int m = match.getHospitalCount(); // number of hospitals
        int n = match.getResidentCount(); // number of residents

        ArrayList<hospital> hospital_list = new ArrayList<hospital>();
        ArrayList<resident> resident_list = new ArrayList<resident>();

        ArrayList<ArrayList<Integer>> resident_preference_list = match.getResidentPreference();
        ArrayList<ArrayList<Integer>> hospital_preference_list = match.getHospitalPreference();

        ArrayList<Integer> hospital_slot_info = marriage.getHospitalSlots();

        /* Hospital List Initialization */
        for(int i = 0; i < m; i++){
            hospital hospital = new hospital();
            hospital.setId(i);
            hospital.setPreference(hospital_preference_list.get(i));
            hospital.setTotal_slots(hospital_slot_info.get(i));
            hospital_list.add(hospital);
        }
        /* Hospital slots initialized to -1 indicating no match */
        for(hospital hospital: hospital_list){
            ArrayList<Integer> temp_list = new ArrayList<Integer>();
            for(int i = 0; i < hospital.getTotal_slots(); i++){
                temp_list.add(-1);
            }
            hospital.setMatched(temp_list);
        }

        /* Resident List Initialization */
        for(int i = 0; i < n; i++){
            resident resident = new resident();
            resident.setId(i);
            resident.setProposal_count(0); //initialize each resident's proposal count to 0
            resident.setPreference(resident_preference_list.get(i));
            resident.setMatch(-1);
            resident_list.add(resident);
        }

        /* checks for instability for every resident */
        for(int i = 0; i < n; i++){

            /* if the resident didn't get his first choice or not matched*/
            if(resident_list.get(i).getMatch() != resident_list.get(i).preference.get(0)){//could be instability

                /* checking for instability */
                //hospital hospital = hospital_list.get(resident_list.get(i).getMatch());//gets the residents currently matched to

                ArrayList<hospital> all_other_hospitals = new ArrayList<>();
//                for(hospital hospitals: hospital_list){
//                    if(hospitals != hospital)
//                        all_other_hospitals.add(hospitals);
//                }

                //System.out.println("should print out all other hospitals");

            }

        }




        return false; /* TODO remove this line */
    }

    /**
     * Determines a resident optimal solution to the Stable Marriage problem from the given input set.
     * Study the project description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMarriageBruteForce_residentoptimal(Matching marriage) {
        /* TODO implement this function */
        return null; /* TODO remove this line */
    }

    /**
     * Determines a resident optimal solution to the Stable Marriage problem from the given input set.
     * Study the description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMarriageGaleShapley_residentoptimal(Matching marriage) {
        Matching match = marriage;

        int m = match.getHospitalCount(); // number of hospitals
        int n = match.getResidentCount(); // number of residents

        ArrayList<hospital> hospital_list = new ArrayList<hospital>();
        ArrayList<resident> resident_list = new ArrayList<resident>();

        ArrayList<ArrayList<Integer>> resident_preference_list = match.getResidentPreference();
        ArrayList<ArrayList<Integer>> hospital_preference_list = match.getHospitalPreference();

        ArrayList<Integer> hospital_slot_info = marriage.getHospitalSlots();

        /* Hospital List Initialization */
        for(int i = 0; i < m; i++){
            hospital hospital = new hospital();
            hospital.setId(i);
            hospital.setPreference(hospital_preference_list.get(i));
            hospital.setTotal_slots(hospital_slot_info.get(i));
            hospital_list.add(hospital);
        }
        /* Hospital slots initialized to -1 indicating no match */
        for(hospital hospital: hospital_list){
            ArrayList<Integer> temp_list = new ArrayList<Integer>();
            for(int i = 0; i < hospital.getTotal_slots(); i++){
                temp_list.add(-1);
            }
            hospital.setMatched(temp_list);
        }

        /* Resident List Initialization */
        for(int i = 0; i < n; i++){
            resident resident = new resident();
            resident.setId(i);
            resident.setProposal_count(0); //initialize each resident's proposal count to 0
            resident.setPreference(resident_preference_list.get(i));
            resident.setMatch(-1);
            resident_list.add(resident);
        }

        /* initialize free resident queue */
        ArrayList<resident> ask_list = new ArrayList<>();
        for(resident resident: resident_list){
            ask_list.add(resident);
        }

        while(!ask_list.isEmpty()){
            resident resident = ask_list.get(0);//gets the first resident in ask list
            ask_list.remove(0);//removes that resident from ask list

            //hospital top_pref = hospital_list.get(resident.preference.get(0));//should return the top preference hospital of resident
            //resident.preference.remove(0);//should remove the top rated off preference list

            hospital top_pref = hospital_list.get((resident.preference.get(resident.proposal_count)));
            resident.proposal_count++;

            if(top_pref.matched.contains(-1)){//this hospital has an open slot
                int index = top_pref.matched.indexOf(-1);//finds the index of open slot
                top_pref.matched.set(index, resident.id);//sets hospital matching to resident id
                resident.match = top_pref.id;//sets the resident matching
            }
            else{//hospital slots are full
                /* returns the least_preferred resident that is currently matched */
                resident least_preferred = resident_list.get(top_pref.findIDLeastPreferred(top_pref.matched, top_pref.preference));
                ArrayList<Integer> hospital_inverse_list = new ArrayList<>();
                hospital_inverse_list = top_pref.getInverseList(top_pref.preference);

                /* initialize flags */
                boolean needsToSwitch = false;
                boolean secondSwitch = false;

                /* hospital prefers current resident to the residents its currently matched with */
                if(hospital_inverse_list.get(least_preferred.id) > hospital_inverse_list.get(resident.id)) {
                    resident.match = top_pref.id;
                    int temp_index = top_pref.matched.indexOf(least_preferred.id);//finds the index of currently matched resident
                    top_pref.matched.set(temp_index, resident.id);
                    least_preferred.match = -1; //unmatches the unfavored resident

                    if(least_preferred.proposal_count >= m)
                        secondSwitch = true;
                    if(!secondSwitch)
                        ask_list.add(least_preferred);//adds back the unfavored resident to the queue
                    needsToSwitch = true;//sets flag
                }
                /* hospital prefers who they're currently matched with */
                if(resident.proposal_count >= m)
                    needsToSwitch = true;
                if(!needsToSwitch)
                    ask_list.add(resident); //add the resident back to the list
            }
        }

        /* Creates and sets the resident matching list */
        ArrayList<Integer> resident_matching = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            int matching = resident_list.get(i).getMatch();
            resident_matching.add(i, matching);
        }
        match.setResidentMatching(resident_matching);
        return match;
    }

    /**
     * Determines a hospital optimal solution to the Stable Marriage problem from the given input set.
     * Study the description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMarriageGaleShapley_hospitaloptimal(Matching marriage) {

        /* initializing inverse preference lists for each resident */
        ArrayList<ArrayList<Integer>> resident_inverse_preference_list = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < marriage.getResidentCount(); i++){
            resident_inverse_preference_list.add(new ArrayList<Integer>());
            for(int j = 0; j < marriage.getHospitalCount(); j++)
                resident_inverse_preference_list.get(i).add(0);
        }

        ArrayList<Integer> resident_matching = new ArrayList<Integer>();

        /* initializing every element in resident matching to -1 and create inverse preference list */
        for(int i = 0; i < marriage.getResidentCount(); i++){
            resident_matching.add(-1);
            for(int j = 0; j < marriage.getHospitalCount(); j++)
                resident_inverse_preference_list.get(i).set(marriage.getResidentPreference().get(i).get(j), j);
        }

        /* keeps track of hospital proposals */
        ArrayList<Integer> hospital_proposal_count = new ArrayList<Integer>();
        for(int i = 0; i < marriage.getHospitalCount(); i++){
            hospital_proposal_count.add(0);
        }

        /* while there exists a hospital with an empty slot */
        while(marriage.totalHospitalSlots() != 0){

            int free_hospital = getIndexOfFreeHospital(marriage.getHospitalSlots());//find the hospital that currently has an open slot
            int top_preferred_resident = marriage.getHospitalPreference().get(free_hospital).get(hospital_proposal_count.get(free_hospital)); // finds top preferred resident which hospital hasn't yet asked

            if(resident_matching.get(top_preferred_resident) == -1){ //means this top_preferred_resident is free

                resident_matching.set(top_preferred_resident, free_hospital);//set the matching
                int temp = hospital_proposal_count.get(free_hospital);
                hospital_proposal_count.set(free_hospital, temp+1);//increment the proposal count
                int slots = marriage.getHospitalSlots().get(free_hospital);
                marriage.getHospitalSlots().set(free_hospital, slots - 1);//decrement the slots available

            }
            /* top_preferred_resident is currently matched with another hospital */

            /* if the resident prefers the one its currently matched with */
            else if(resident_inverse_preference_list.get(top_preferred_resident).get(resident_matching.get(top_preferred_resident)) < resident_inverse_preference_list.get(top_preferred_resident).get(free_hospital)){
                int temp = hospital_proposal_count.get(free_hospital);
                hospital_proposal_count.set(free_hospital, temp+1);//increase the proposal count
            }
            /* if the resident prefers the new hospital over the one its currently matched with */
            else{
                int temp_free = resident_matching.get(top_preferred_resident);//hospital its currently matched with
                resident_matching.set(top_preferred_resident, free_hospital);
                int slots = marriage.getHospitalSlots().get(free_hospital);
                marriage.getHospitalSlots().set(free_hospital, slots - 1);//decrement the slots available
                int slots2 = marriage.getHospitalSlots().get(temp_free);
                marriage.getHospitalSlots().set(temp_free, slots2+1);//increment slots available for hospital that swapped out
                int temp = hospital_proposal_count.get(free_hospital);
                hospital_proposal_count.set(free_hospital, temp+1);//increment the proposal count of the hospital
            }
        }
        marriage.setResidentMatching(resident_matching);//set the resident matching
        return  marriage;
    }

    int getIndexOfFreeHospital(ArrayList<Integer> slots){
        for(int i = 0; i < slots.size(); i++){
            if(slots.get(i) > 0)
                return i;
        }
        return -1;
    }
}


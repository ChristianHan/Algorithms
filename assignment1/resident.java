package assignment1;

import java.util.ArrayList;

public class resident {

    public int id;
    public int proposal_count;
    public int match;
    public ArrayList<Integer> preference;

    public resident(){
        int id;
        int proposal_count;
        int match;
        ArrayList<Integer> preference;
    }

    public int findIDLeastPreferred(ArrayList<Integer> matched, ArrayList<Integer> preference){

        int id = 0;

        for(int i = 0; i < preference.size(); i++){
            for(int hospital : matched){
                if(preference.get(i) == hospital)
                    id = hospital;
            }
        }
        return  id;
    }

//    public int findIDLeastPreferred(int matched, ArrayList<Integer> current_matches){
//
//        int id = 0;
//
//        for(int i = 0; i < current_matches.size(); i++){
//                if(current_matches.get(i) == matched)
//                    id = matched;
//        }
//        return  id;
//    }

    public ArrayList<Integer> getInverseList(ArrayList<Integer> list){ //returns inverse list of list


        ArrayList<Integer> inverse_arrayList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            inverse_arrayList.add(0);
        }
        for(int i = 0; i < list.size(); i++)
            inverse_arrayList.set(list.get(i), i);
        return inverse_arrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProposal_count() {
        return proposal_count;
    }

    public void setProposal_count(int proposal_count) {
        this.proposal_count = proposal_count;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public ArrayList<Integer> getPreference() {
        return preference;
    }

    public void setPreference(ArrayList<Integer> preference) {
        this.preference = preference;
    }



}

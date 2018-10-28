package assignment1;

import java.util.ArrayList;

public class hospital {

    public int id;
    public int total_slots;
    public int proposal_count;
    public ArrayList<Integer> matched;
    public ArrayList<Integer> preference;
    //public ArrayList<Integer> inverse_preference;

    public hospital(){
        int id;
        int total_slots;
        ArrayList<Integer> matched;
        ArrayList<Integer> preference;
        //ArrayList<Integer> inverse_preference;
    }

    public int findIDLeastPreferred(ArrayList<Integer> matched, ArrayList<Integer> preference){

        int id = 0;

        for(int i = 0; i < preference.size(); i++){
            for(int resident : matched){
                if(preference.get(i) == resident)
                    id = resident;
            }
        }
        return  id;
    }

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

    public int getTotal_slots() {
        return total_slots;
    }

    public void setTotal_slots(int total_slots) {
        this.total_slots = total_slots;
    }

//    public int getSlots_available() {
//        return slots_available;
//    }
//
//    public void setSlots_available(int slots_available) {
//        this.slots_available = slots_available;
//    }

    public ArrayList<Integer> getMatched() {
        return matched;
    }

    public void setMatched(ArrayList<Integer> matched) {
        this.matched = matched;
    }

    public ArrayList<Integer> getPreference() {
        return preference;
    }

    public void setPreference(ArrayList<Integer> preference) {
        this.preference = preference;
    }
}

class Identification implements Runnable {

    SearchIsland searchIsland;
    Identification(SearchIsland searchIsland){
        this.searchIsland=searchIsland;
    }
    public void run(){
        while(searchIsland.island()){
        }
        searchIsland.show();
    }
}

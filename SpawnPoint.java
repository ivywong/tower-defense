public class SpawnPoint{
    protected Coordinate coor;
    protected int spawnDelay; //how long to wait between each monster
    protected int spawnBudget; //how many monsters to spawn

    public SpawnPoint(){
	this(600,300);
    }
    public SpawnPoint(int x, int y){
	this(new Coordinate(x, y));
    }
   
    public SpawnPoint(Coordinate coor){
	this.coor = coor;
	spawnDelay = 10;
	spawnBudget = 50;
    }

    // public static void spawn(){  //helppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp
    // 	if (spawnBudget > 0){  //better idea: just use spawnpoint to check for delay, budget, move spawn code to map again
    // 	    if (spawnDelay <= 0){
		
    // 	    }
    // 	}
    // }
}

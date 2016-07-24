package sample;

public class EnemyFactory {
	public Character createUnit(EnemyTypes type)
    {
        Character unit = null;
        switch(type)
        {
            case DROID:
            	unit = new Droid();
            	break;
            	
            case CLONE:
            	unit = new Clone();
            	break;
            	
            case SOLDAT:
            	unit = new Soldat();
            	break;
        }
        return unit;
    }
}
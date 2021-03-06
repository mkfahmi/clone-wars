package sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnemyTypes {
    DROID,
    CLONE,
    SOLDAT;

    private static final List<EnemyTypes> VALUES =
            Collections.unmodifiableList(Arrays.asList(EnemyTypes.DROID, EnemyTypes.CLONE, EnemyTypes.CLONE, EnemyTypes.SOLDAT, EnemyTypes.SOLDAT));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EnemyTypes randomEnemy()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
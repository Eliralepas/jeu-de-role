package utils;

import java.util.Random;

public abstract class De {
    public static int lance(int nbFaces){
        return new Random().nextInt(nbFaces) + 1;
    }
}

package net.ictcampus.javamodul.lang;

import java.io.Serial;

public class OutOfRangeException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -8637231415074527334L;

    public OutOfRangeException(String str) {
        super(str);
    }

}

package com.destinymc.ic2.cable;

import com.destinymc.util.ArrayUtil;
import org.bukkit.block.BlockFace;

import java.util.List;

import static org.bukkit.block.BlockFace.*;

/**
 * Created at 14/03/2019
 *
 * @author Luke Bingham
 */
public enum CableShapeType {

    /*
     *   N
     * W + E
     *   S
     */

    UP_DOWN(1000, new BlockFace[]{DOWN, UP}, null), //Straight
    UP_(1000, new BlockFace[]{UP}, null), //Straight
    DOWN_(1000, new BlockFace[]{DOWN}, null), //Straight

    NORTH_SOUTH(1001, new BlockFace[]{NORTH, SOUTH}, null), //Straight
    NORTH_(1001, new BlockFace[]{NORTH}, null), //Straight
    SOUTH_(1001, new BlockFace[]{SOUTH}, null), //Straight
    EAST_WEST(1002, new BlockFace[]{EAST, WEST}, null), //Straight
    EAST_(1002, new BlockFace[]{EAST}, null), //Straight
    WEST_(1002, new BlockFace[]{WEST}, null), //Straight
    NORTH_EAST(1004, new BlockFace[]{NORTH, EAST}, null), //Right Angle (-> north west 1004) DONE
    NORTH_WEST(1006, new BlockFace[]{NORTH, WEST}, null), //Right Angle (-> south west 1006) DONE
    SOUTH_EAST(1003, new BlockFace[]{SOUTH, EAST}, null), //Right Angle (-> north east 1003) DONE
    SOUTH_WEST(1005, new BlockFace[]{SOUTH, WEST}, null), //Right Angle (-> south east 1005) DONE
    NORTH_WEST_EAST(1010, new BlockFace[]{NORTH, WEST, EAST}, null), //Two Right Angle (-> s w n 1010) DONE
    NORTH_EAST_SOUTH(1007, new BlockFace[]{NORTH, EAST, SOUTH}, null), //Two Right Angle (-> n w e 1007) DONE
    SOUTH_WEST_EAST(1008, new BlockFace[]{SOUTH, WEST, EAST}, null), //Two Right Angle (-> n e s 1008) DONE
    SOUTH_WEST_NORTH(1009, new BlockFace[]{SOUTH, WEST, NORTH}, null), //Two Right Angle (-> s w e 1009) DONE
    NORTH_EAST_SOUTH_WEST(1011, new BlockFace[]{SOUTH, WEST, NORTH, EAST}, null), //Four Right Angle

    UP_NORTH_SOUTH(1023, new BlockFace[]{UP, NORTH, SOUTH}, null), //Straight
    UP_NORTH(1023, new BlockFace[]{UP, NORTH}, null), //Straight
    UP_SOUTH(1023, new BlockFace[]{UP, SOUTH}, null), //Straight
    UP_EAST_WEST(1024, new BlockFace[]{UP, EAST, WEST}, null), //Straight
    UP_EAST(1024, new BlockFace[]{UP, EAST}, null), //Straight
    UP_WEST(1024, new BlockFace[]{UP, WEST}, null), //Straight
    UP_NORTH_EAST(1026, new BlockFace[]{NORTH, EAST, UP}, null), //Right Angle (-> north west 1026) DONE
    UP_NORTH_WEST(1028, new BlockFace[]{NORTH, WEST, UP}, null), //Right Angle (-> south west 1028) DONE
    UP_SOUTH_EAST(1025, new BlockFace[]{SOUTH, EAST, UP}, null), //Right Angle (-> north east 1025) DONE
    UP_SOUTH_WEST(1027, new BlockFace[]{SOUTH, WEST, UP}, null), //Right Angle (-> south east 1027) DONE
    UP_NORTH_WEST_EAST(1032, new BlockFace[]{NORTH, WEST, EAST, UP}, null), //Two Right Angle (-> s w n 1032) DONE
    UP_NORTH_EAST_SOUTH(1029, new BlockFace[]{NORTH, EAST, SOUTH, UP}, null), //Two Right Angle (-> n w e 1029) DONE
    UP_SOUTH_WEST_EAST(1030, new BlockFace[]{SOUTH, WEST, EAST, UP}, null), //Two Right Angle (-> n e s 1030) DONE
    UP_SOUTH_WEST_NORTH(1031, new BlockFace[]{SOUTH, WEST, NORTH, UP}, null), //Two Right Angle (-> s w e 1031) DONE
    UP_NORTH_EAST_SOUTH_WEST(1033, new BlockFace[]{SOUTH, WEST, NORTH, EAST, UP}, null), //Four Right Angle

    DOWN_NORTH_SOUTH(1012, new BlockFace[]{DOWN, NORTH, SOUTH}, null), //Straight
    DOWN_NORTH(1012, new BlockFace[]{DOWN, NORTH}, null), //Straight
    DOWN_SOUTH(1012, new BlockFace[]{DOWN, SOUTH}, null), //Straight
    DOWN_EAST_WEST(1013, new BlockFace[]{DOWN, EAST, WEST}, null), //Straight
    DOWN_EAST(1013, new BlockFace[]{DOWN, EAST}, null), //Straight
    DOWN_WEST(1013, new BlockFace[]{DOWN, WEST}, null), //Straight
    DOWN_NORTH_EAST(1015, new BlockFace[]{NORTH, EAST, DOWN}, null), //Right Angle (-> north west 1015) DONE
    DOWN_NORTH_WEST(1017, new BlockFace[]{NORTH, WEST, DOWN}, null), //Right Angle (-> south west 1017) DONE
    DOWN_SOUTH_EAST(1014, new BlockFace[]{SOUTH, EAST, DOWN}, null), //Right Angle (-> north east 1014) DONE
    DOWN_SOUTH_WEST(1016, new BlockFace[]{SOUTH, WEST, DOWN}, null), //Right Angle (-> south east 1016) DONE
    DOWN_NORTH_WEST_EAST(1021, new BlockFace[]{NORTH, WEST, EAST, DOWN}, null), //Two Right Angle (-> s w n 1021) DONE
    DOWN_NORTH_EAST_SOUTH(1018, new BlockFace[]{NORTH, EAST, SOUTH, DOWN}, null), //Two Right Angle (-> n w e 1018) DONE
    DOWN_SOUTH_WEST_EAST(1019, new BlockFace[]{SOUTH, WEST, EAST, DOWN}, null), //Two Right Angle (-> n e s 1019) DONE
    DOWN_SOUTH_WEST_NORTH(1020, new BlockFace[]{SOUTH, WEST, NORTH, DOWN}, null), //Two Right Angle (-> s w e 1020) DONE
    DOWN_NORTH_EAST_SOUTH_WEST(1022, new BlockFace[]{SOUTH, WEST, NORTH, EAST, DOWN}, null), //Four Right Angle

    DOWN_UP_NORTH_SOUTH(1034, new BlockFace[]{DOWN, UP, NORTH, SOUTH}, null), //Straight
    DOWN_UP_NORTH(1034, new BlockFace[]{DOWN, UP, NORTH}, null), //Straight
    DOWN_UP_SOUTH(1034, new BlockFace[]{DOWN, UP, SOUTH}, null), //Straight
    DOWN_UP_EAST_WEST(1035, new BlockFace[]{DOWN, UP, EAST, WEST}, null), //Straight
    DOWN_UP_EAST(1035, new BlockFace[]{DOWN, UP, EAST}, null), //Straight
    DOWN_UP_WEST(1035, new BlockFace[]{DOWN, UP, WEST}, null), //Straight
    DOWN_UP_NORTH_EAST(1037, new BlockFace[]{NORTH, EAST, DOWN, UP}, null), //Right Angle (-> north west 1037) DONE
    DOWN_UP_NORTH_WEST(1039, new BlockFace[]{NORTH, WEST, DOWN, UP}, null), //Right Angle (-> south west 1039) DONE
    DOWN_UP_SOUTH_EAST(1036, new BlockFace[]{SOUTH, EAST, DOWN, UP}, null), //Right Angle (-> north east 1036) DONE
    DOWN_UP_SOUTH_WEST(1038, new BlockFace[]{SOUTH, WEST, DOWN, UP}, null), //Right Angle (-> south east 1038) DONE
    DOWN_UP_NORTH_WEST_EAST(1043, new BlockFace[]{NORTH, WEST, EAST, DOWN, UP}, null), //Two Right Angle (-> s w n 1043) DONE
    DOWN_UP_NORTH_EAST_SOUTH(1040, new BlockFace[]{NORTH, EAST, SOUTH, DOWN, UP}, null), //Two Right Angle (-> n w e 1040) DONE
    DOWN_UP_SOUTH_WEST_EAST(1041, new BlockFace[]{SOUTH, WEST, EAST, DOWN, UP}, null), //Two Right Angle (-> n e s 1041) DONE
    DOWN_UP_SOUTH_WEST_NORTH(1042, new BlockFace[]{SOUTH, WEST, NORTH, DOWN, UP}, null), //Two Right Angle (-> s w e 1042) DONE
    DOWN_UP_NORTH_EAST_SOUTH_WEST(1044, new BlockFace[]{SOUTH, WEST, NORTH, EAST, DOWN, UP}, null), //Four Right Angle

    ;

    private final int id;
    private final BlockFace[] required;
    private final BlockFace[] optional;

    CableShapeType(int id, BlockFace[] required, BlockFace[] optional) {
        this.id = id;
        this.required = required;
        this.optional = optional;
    }

    public BlockFace[] getRequired() {
        return required;
    }

    public BlockFace[] getOptional() {
        return optional;
    }

    public boolean isOptional() {
        if (optional == null)
            return false;

        return optional.length > 0;
    }

    public static CableShapeType getShape(BlockFace[] faceArray) {
        for (int i = values().length - 1; i >= 0; i--) {
            CableShapeType shape = values()[i];

            if (shape.required.length != faceArray.length)
                continue;

            boolean failed = false;
            for (BlockFace face : shape.required) {
                if (!ArrayUtil.containsElement(face, faceArray)) {
                    failed = true;
                    break;
                }
            }
            if (failed) continue;

//            if (shape.isOptional()) {
//                boolean optionalFound = false;
//                for (BlockFace face : shape.optional) {
//                    if (ArrayUtil.containsElement(face, faceArray)) {
//                        optionalFound = true;
//                        break;
//                    }
//                }
//
//                if (!optionalFound)
//                    continue;
//            }

            System.out.println("true - " + shape.name());
            return shape;
        }

        System.out.println("false - " + NORTH_SOUTH.name());
        return NORTH_SOUTH;
    }

    public static CableShapeType getShape(List<BlockFace> faceArray) {
        BlockFace[] array = new BlockFace[faceArray.size()];
        array = faceArray.toArray(array);
        return getShape(array);
    }

    public int getId() {
        return id;
    }

    public static BlockFace[] SUPPORTED = {SOUTH, WEST, NORTH, EAST, DOWN, UP};
}

package com.syncforge.path;
import com.syncforge.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MassivePathSafetyTest {
    @Test
    public void testPathSafetyCase_1() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_1/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_1/../traversal"));
    }
    @Test
    public void testPathSafetyCase_2() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_2/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_2/../traversal"));
    }
    @Test
    public void testPathSafetyCase_3() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_3/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_3/../traversal"));
    }
    @Test
    public void testPathSafetyCase_4() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_4/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_4/../traversal"));
    }
    @Test
    public void testPathSafetyCase_5() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_5/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_5/../traversal"));
    }
    @Test
    public void testPathSafetyCase_6() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_6/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_6/../traversal"));
    }
    @Test
    public void testPathSafetyCase_7() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_7/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_7/../traversal"));
    }
    @Test
    public void testPathSafetyCase_8() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_8/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_8/../traversal"));
    }
    @Test
    public void testPathSafetyCase_9() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_9/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_9/../traversal"));
    }
    @Test
    public void testPathSafetyCase_10() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_10/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_10/../traversal"));
    }
    @Test
    public void testPathSafetyCase_11() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_11/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_11/../traversal"));
    }
    @Test
    public void testPathSafetyCase_12() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_12/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_12/../traversal"));
    }
    @Test
    public void testPathSafetyCase_13() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_13/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_13/../traversal"));
    }
    @Test
    public void testPathSafetyCase_14() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_14/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_14/../traversal"));
    }
    @Test
    public void testPathSafetyCase_15() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_15/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_15/../traversal"));
    }
    @Test
    public void testPathSafetyCase_16() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_16/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_16/../traversal"));
    }
    @Test
    public void testPathSafetyCase_17() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_17/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_17/../traversal"));
    }
    @Test
    public void testPathSafetyCase_18() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_18/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_18/../traversal"));
    }
    @Test
    public void testPathSafetyCase_19() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_19/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_19/../traversal"));
    }
    @Test
    public void testPathSafetyCase_20() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_20/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_20/../traversal"));
    }
    @Test
    public void testPathSafetyCase_21() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_21/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_21/../traversal"));
    }
    @Test
    public void testPathSafetyCase_22() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_22/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_22/../traversal"));
    }
    @Test
    public void testPathSafetyCase_23() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_23/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_23/../traversal"));
    }
    @Test
    public void testPathSafetyCase_24() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_24/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_24/../traversal"));
    }
    @Test
    public void testPathSafetyCase_25() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_25/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_25/../traversal"));
    }
    @Test
    public void testPathSafetyCase_26() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_26/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_26/../traversal"));
    }
    @Test
    public void testPathSafetyCase_27() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_27/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_27/../traversal"));
    }
    @Test
    public void testPathSafetyCase_28() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_28/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_28/../traversal"));
    }
    @Test
    public void testPathSafetyCase_29() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_29/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_29/../traversal"));
    }
    @Test
    public void testPathSafetyCase_30() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_30/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_30/../traversal"));
    }
    @Test
    public void testPathSafetyCase_31() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_31/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_31/../traversal"));
    }
    @Test
    public void testPathSafetyCase_32() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_32/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_32/../traversal"));
    }
    @Test
    public void testPathSafetyCase_33() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_33/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_33/../traversal"));
    }
    @Test
    public void testPathSafetyCase_34() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_34/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_34/../traversal"));
    }
    @Test
    public void testPathSafetyCase_35() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_35/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_35/../traversal"));
    }
    @Test
    public void testPathSafetyCase_36() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_36/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_36/../traversal"));
    }
    @Test
    public void testPathSafetyCase_37() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_37/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_37/../traversal"));
    }
    @Test
    public void testPathSafetyCase_38() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_38/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_38/../traversal"));
    }
    @Test
    public void testPathSafetyCase_39() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_39/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_39/../traversal"));
    }
    @Test
    public void testPathSafetyCase_40() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_40/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_40/../traversal"));
    }
    @Test
    public void testPathSafetyCase_41() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_41/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_41/../traversal"));
    }
    @Test
    public void testPathSafetyCase_42() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_42/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_42/../traversal"));
    }
    @Test
    public void testPathSafetyCase_43() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_43/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_43/../traversal"));
    }
    @Test
    public void testPathSafetyCase_44() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_44/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_44/../traversal"));
    }
    @Test
    public void testPathSafetyCase_45() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_45/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_45/../traversal"));
    }
    @Test
    public void testPathSafetyCase_46() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_46/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_46/../traversal"));
    }
    @Test
    public void testPathSafetyCase_47() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_47/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_47/../traversal"));
    }
    @Test
    public void testPathSafetyCase_48() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_48/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_48/../traversal"));
    }
    @Test
    public void testPathSafetyCase_49() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_49/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_49/../traversal"));
    }
    @Test
    public void testPathSafetyCase_50() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_50/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_50/../traversal"));
    }
    @Test
    public void testPathSafetyCase_51() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_51/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_51/../traversal"));
    }
    @Test
    public void testPathSafetyCase_52() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_52/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_52/../traversal"));
    }
    @Test
    public void testPathSafetyCase_53() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_53/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_53/../traversal"));
    }
    @Test
    public void testPathSafetyCase_54() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_54/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_54/../traversal"));
    }
    @Test
    public void testPathSafetyCase_55() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_55/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_55/../traversal"));
    }
    @Test
    public void testPathSafetyCase_56() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_56/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_56/../traversal"));
    }
    @Test
    public void testPathSafetyCase_57() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_57/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_57/../traversal"));
    }
    @Test
    public void testPathSafetyCase_58() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_58/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_58/../traversal"));
    }
    @Test
    public void testPathSafetyCase_59() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_59/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_59/../traversal"));
    }
    @Test
    public void testPathSafetyCase_60() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_60/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_60/../traversal"));
    }
    @Test
    public void testPathSafetyCase_61() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_61/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_61/../traversal"));
    }
    @Test
    public void testPathSafetyCase_62() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_62/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_62/../traversal"));
    }
    @Test
    public void testPathSafetyCase_63() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_63/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_63/../traversal"));
    }
    @Test
    public void testPathSafetyCase_64() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_64/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_64/../traversal"));
    }
    @Test
    public void testPathSafetyCase_65() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_65/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_65/../traversal"));
    }
    @Test
    public void testPathSafetyCase_66() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_66/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_66/../traversal"));
    }
    @Test
    public void testPathSafetyCase_67() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_67/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_67/../traversal"));
    }
    @Test
    public void testPathSafetyCase_68() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_68/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_68/../traversal"));
    }
    @Test
    public void testPathSafetyCase_69() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_69/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_69/../traversal"));
    }
    @Test
    public void testPathSafetyCase_70() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_70/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_70/../traversal"));
    }
    @Test
    public void testPathSafetyCase_71() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_71/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_71/../traversal"));
    }
    @Test
    public void testPathSafetyCase_72() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_72/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_72/../traversal"));
    }
    @Test
    public void testPathSafetyCase_73() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_73/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_73/../traversal"));
    }
    @Test
    public void testPathSafetyCase_74() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_74/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_74/../traversal"));
    }
    @Test
    public void testPathSafetyCase_75() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_75/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_75/../traversal"));
    }
    @Test
    public void testPathSafetyCase_76() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_76/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_76/../traversal"));
    }
    @Test
    public void testPathSafetyCase_77() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_77/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_77/../traversal"));
    }
    @Test
    public void testPathSafetyCase_78() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_78/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_78/../traversal"));
    }
    @Test
    public void testPathSafetyCase_79() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_79/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_79/../traversal"));
    }
    @Test
    public void testPathSafetyCase_80() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_80/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_80/../traversal"));
    }
    @Test
    public void testPathSafetyCase_81() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_81/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_81/../traversal"));
    }
    @Test
    public void testPathSafetyCase_82() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_82/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_82/../traversal"));
    }
    @Test
    public void testPathSafetyCase_83() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_83/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_83/../traversal"));
    }
    @Test
    public void testPathSafetyCase_84() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_84/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_84/../traversal"));
    }
    @Test
    public void testPathSafetyCase_85() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_85/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_85/../traversal"));
    }
    @Test
    public void testPathSafetyCase_86() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_86/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_86/../traversal"));
    }
    @Test
    public void testPathSafetyCase_87() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_87/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_87/../traversal"));
    }
    @Test
    public void testPathSafetyCase_88() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_88/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_88/../traversal"));
    }
    @Test
    public void testPathSafetyCase_89() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_89/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_89/../traversal"));
    }
    @Test
    public void testPathSafetyCase_90() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_90/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_90/../traversal"));
    }
    @Test
    public void testPathSafetyCase_91() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_91/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_91/../traversal"));
    }
    @Test
    public void testPathSafetyCase_92() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_92/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_92/../traversal"));
    }
    @Test
    public void testPathSafetyCase_93() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_93/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_93/../traversal"));
    }
    @Test
    public void testPathSafetyCase_94() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_94/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_94/../traversal"));
    }
    @Test
    public void testPathSafetyCase_95() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_95/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_95/../traversal"));
    }
    @Test
    public void testPathSafetyCase_96() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_96/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_96/../traversal"));
    }
    @Test
    public void testPathSafetyCase_97() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_97/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_97/../traversal"));
    }
    @Test
    public void testPathSafetyCase_98() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_98/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_98/../traversal"));
    }
    @Test
    public void testPathSafetyCase_99() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_99/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_99/../traversal"));
    }
    @Test
    public void testPathSafetyCase_100() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_100/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_100/../traversal"));
    }
    @Test
    public void testPathSafetyCase_101() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_101/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_101/../traversal"));
    }
    @Test
    public void testPathSafetyCase_102() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_102/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_102/../traversal"));
    }
    @Test
    public void testPathSafetyCase_103() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_103/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_103/../traversal"));
    }
    @Test
    public void testPathSafetyCase_104() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_104/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_104/../traversal"));
    }
    @Test
    public void testPathSafetyCase_105() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_105/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_105/../traversal"));
    }
    @Test
    public void testPathSafetyCase_106() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_106/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_106/../traversal"));
    }
    @Test
    public void testPathSafetyCase_107() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_107/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_107/../traversal"));
    }
    @Test
    public void testPathSafetyCase_108() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_108/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_108/../traversal"));
    }
    @Test
    public void testPathSafetyCase_109() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_109/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_109/../traversal"));
    }
    @Test
    public void testPathSafetyCase_110() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_110/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_110/../traversal"));
    }
    @Test
    public void testPathSafetyCase_111() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_111/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_111/../traversal"));
    }
    @Test
    public void testPathSafetyCase_112() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_112/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_112/../traversal"));
    }
    @Test
    public void testPathSafetyCase_113() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_113/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_113/../traversal"));
    }
    @Test
    public void testPathSafetyCase_114() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_114/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_114/../traversal"));
    }
    @Test
    public void testPathSafetyCase_115() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_115/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_115/../traversal"));
    }
    @Test
    public void testPathSafetyCase_116() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_116/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_116/../traversal"));
    }
    @Test
    public void testPathSafetyCase_117() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_117/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_117/../traversal"));
    }
    @Test
    public void testPathSafetyCase_118() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_118/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_118/../traversal"));
    }
    @Test
    public void testPathSafetyCase_119() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_119/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_119/../traversal"));
    }
    @Test
    public void testPathSafetyCase_120() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_120/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_120/../traversal"));
    }
    @Test
    public void testPathSafetyCase_121() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_121/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_121/../traversal"));
    }
    @Test
    public void testPathSafetyCase_122() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_122/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_122/../traversal"));
    }
    @Test
    public void testPathSafetyCase_123() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_123/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_123/../traversal"));
    }
    @Test
    public void testPathSafetyCase_124() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_124/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_124/../traversal"));
    }
    @Test
    public void testPathSafetyCase_125() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_125/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_125/../traversal"));
    }
    @Test
    public void testPathSafetyCase_126() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_126/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_126/../traversal"));
    }
    @Test
    public void testPathSafetyCase_127() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_127/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_127/../traversal"));
    }
    @Test
    public void testPathSafetyCase_128() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_128/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_128/../traversal"));
    }
    @Test
    public void testPathSafetyCase_129() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_129/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_129/../traversal"));
    }
    @Test
    public void testPathSafetyCase_130() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_130/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_130/../traversal"));
    }
    @Test
    public void testPathSafetyCase_131() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_131/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_131/../traversal"));
    }
    @Test
    public void testPathSafetyCase_132() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_132/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_132/../traversal"));
    }
    @Test
    public void testPathSafetyCase_133() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_133/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_133/../traversal"));
    }
    @Test
    public void testPathSafetyCase_134() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_134/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_134/../traversal"));
    }
    @Test
    public void testPathSafetyCase_135() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_135/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_135/../traversal"));
    }
    @Test
    public void testPathSafetyCase_136() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_136/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_136/../traversal"));
    }
    @Test
    public void testPathSafetyCase_137() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_137/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_137/../traversal"));
    }
    @Test
    public void testPathSafetyCase_138() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_138/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_138/../traversal"));
    }
    @Test
    public void testPathSafetyCase_139() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_139/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_139/../traversal"));
    }
    @Test
    public void testPathSafetyCase_140() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_140/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_140/../traversal"));
    }
    @Test
    public void testPathSafetyCase_141() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_141/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_141/../traversal"));
    }
    @Test
    public void testPathSafetyCase_142() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_142/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_142/../traversal"));
    }
    @Test
    public void testPathSafetyCase_143() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_143/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_143/../traversal"));
    }
    @Test
    public void testPathSafetyCase_144() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_144/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_144/../traversal"));
    }
    @Test
    public void testPathSafetyCase_145() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_145/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_145/../traversal"));
    }
    @Test
    public void testPathSafetyCase_146() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_146/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_146/../traversal"));
    }
    @Test
    public void testPathSafetyCase_147() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_147/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_147/../traversal"));
    }
    @Test
    public void testPathSafetyCase_148() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_148/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_148/../traversal"));
    }
    @Test
    public void testPathSafetyCase_149() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_149/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_149/../traversal"));
    }
    @Test
    public void testPathSafetyCase_150() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_150/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_150/../traversal"));
    }
    @Test
    public void testPathSafetyCase_151() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_151/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_151/../traversal"));
    }
    @Test
    public void testPathSafetyCase_152() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_152/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_152/../traversal"));
    }
    @Test
    public void testPathSafetyCase_153() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_153/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_153/../traversal"));
    }
    @Test
    public void testPathSafetyCase_154() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_154/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_154/../traversal"));
    }
    @Test
    public void testPathSafetyCase_155() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_155/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_155/../traversal"));
    }
    @Test
    public void testPathSafetyCase_156() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_156/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_156/../traversal"));
    }
    @Test
    public void testPathSafetyCase_157() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_157/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_157/../traversal"));
    }
    @Test
    public void testPathSafetyCase_158() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_158/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_158/../traversal"));
    }
    @Test
    public void testPathSafetyCase_159() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_159/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_159/../traversal"));
    }
    @Test
    public void testPathSafetyCase_160() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_160/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_160/../traversal"));
    }
    @Test
    public void testPathSafetyCase_161() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_161/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_161/../traversal"));
    }
    @Test
    public void testPathSafetyCase_162() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_162/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_162/../traversal"));
    }
    @Test
    public void testPathSafetyCase_163() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_163/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_163/../traversal"));
    }
    @Test
    public void testPathSafetyCase_164() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_164/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_164/../traversal"));
    }
    @Test
    public void testPathSafetyCase_165() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_165/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_165/../traversal"));
    }
    @Test
    public void testPathSafetyCase_166() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_166/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_166/../traversal"));
    }
    @Test
    public void testPathSafetyCase_167() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_167/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_167/../traversal"));
    }
    @Test
    public void testPathSafetyCase_168() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_168/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_168/../traversal"));
    }
    @Test
    public void testPathSafetyCase_169() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_169/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_169/../traversal"));
    }
    @Test
    public void testPathSafetyCase_170() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_170/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_170/../traversal"));
    }
    @Test
    public void testPathSafetyCase_171() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_171/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_171/../traversal"));
    }
    @Test
    public void testPathSafetyCase_172() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_172/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_172/../traversal"));
    }
    @Test
    public void testPathSafetyCase_173() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_173/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_173/../traversal"));
    }
    @Test
    public void testPathSafetyCase_174() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_174/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_174/../traversal"));
    }
    @Test
    public void testPathSafetyCase_175() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_175/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_175/../traversal"));
    }
    @Test
    public void testPathSafetyCase_176() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_176/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_176/../traversal"));
    }
    @Test
    public void testPathSafetyCase_177() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_177/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_177/../traversal"));
    }
    @Test
    public void testPathSafetyCase_178() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_178/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_178/../traversal"));
    }
    @Test
    public void testPathSafetyCase_179() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_179/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_179/../traversal"));
    }
    @Test
    public void testPathSafetyCase_180() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_180/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_180/../traversal"));
    }
    @Test
    public void testPathSafetyCase_181() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_181/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_181/../traversal"));
    }
    @Test
    public void testPathSafetyCase_182() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_182/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_182/../traversal"));
    }
    @Test
    public void testPathSafetyCase_183() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_183/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_183/../traversal"));
    }
    @Test
    public void testPathSafetyCase_184() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_184/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_184/../traversal"));
    }
    @Test
    public void testPathSafetyCase_185() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_185/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_185/../traversal"));
    }
    @Test
    public void testPathSafetyCase_186() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_186/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_186/../traversal"));
    }
    @Test
    public void testPathSafetyCase_187() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_187/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_187/../traversal"));
    }
    @Test
    public void testPathSafetyCase_188() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_188/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_188/../traversal"));
    }
    @Test
    public void testPathSafetyCase_189() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_189/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_189/../traversal"));
    }
    @Test
    public void testPathSafetyCase_190() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_190/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_190/../traversal"));
    }
    @Test
    public void testPathSafetyCase_191() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_191/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_191/../traversal"));
    }
    @Test
    public void testPathSafetyCase_192() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_192/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_192/../traversal"));
    }
    @Test
    public void testPathSafetyCase_193() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_193/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_193/../traversal"));
    }
    @Test
    public void testPathSafetyCase_194() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_194/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_194/../traversal"));
    }
    @Test
    public void testPathSafetyCase_195() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_195/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_195/../traversal"));
    }
    @Test
    public void testPathSafetyCase_196() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_196/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_196/../traversal"));
    }
    @Test
    public void testPathSafetyCase_197() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_197/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_197/../traversal"));
    }
    @Test
    public void testPathSafetyCase_198() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_198/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_198/../traversal"));
    }
    @Test
    public void testPathSafetyCase_199() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_199/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_199/../traversal"));
    }
    @Test
    public void testPathSafetyCase_200() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_200/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_200/../traversal"));
    }
    @Test
    public void testPathSafetyCase_201() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_201/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_201/../traversal"));
    }
    @Test
    public void testPathSafetyCase_202() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_202/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_202/../traversal"));
    }
    @Test
    public void testPathSafetyCase_203() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_203/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_203/../traversal"));
    }
    @Test
    public void testPathSafetyCase_204() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_204/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_204/../traversal"));
    }
    @Test
    public void testPathSafetyCase_205() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_205/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_205/../traversal"));
    }
    @Test
    public void testPathSafetyCase_206() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_206/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_206/../traversal"));
    }
    @Test
    public void testPathSafetyCase_207() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_207/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_207/../traversal"));
    }
    @Test
    public void testPathSafetyCase_208() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_208/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_208/../traversal"));
    }
    @Test
    public void testPathSafetyCase_209() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_209/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_209/../traversal"));
    }
    @Test
    public void testPathSafetyCase_210() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_210/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_210/../traversal"));
    }
    @Test
    public void testPathSafetyCase_211() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_211/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_211/../traversal"));
    }
    @Test
    public void testPathSafetyCase_212() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_212/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_212/../traversal"));
    }
    @Test
    public void testPathSafetyCase_213() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_213/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_213/../traversal"));
    }
    @Test
    public void testPathSafetyCase_214() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_214/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_214/../traversal"));
    }
    @Test
    public void testPathSafetyCase_215() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_215/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_215/../traversal"));
    }
    @Test
    public void testPathSafetyCase_216() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_216/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_216/../traversal"));
    }
    @Test
    public void testPathSafetyCase_217() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_217/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_217/../traversal"));
    }
    @Test
    public void testPathSafetyCase_218() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_218/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_218/../traversal"));
    }
    @Test
    public void testPathSafetyCase_219() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_219/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_219/../traversal"));
    }
    @Test
    public void testPathSafetyCase_220() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_220/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_220/../traversal"));
    }
    @Test
    public void testPathSafetyCase_221() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_221/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_221/../traversal"));
    }
    @Test
    public void testPathSafetyCase_222() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_222/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_222/../traversal"));
    }
    @Test
    public void testPathSafetyCase_223() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_223/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_223/../traversal"));
    }
    @Test
    public void testPathSafetyCase_224() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_224/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_224/../traversal"));
    }
    @Test
    public void testPathSafetyCase_225() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_225/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_225/../traversal"));
    }
    @Test
    public void testPathSafetyCase_226() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_226/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_226/../traversal"));
    }
    @Test
    public void testPathSafetyCase_227() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_227/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_227/../traversal"));
    }
    @Test
    public void testPathSafetyCase_228() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_228/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_228/../traversal"));
    }
    @Test
    public void testPathSafetyCase_229() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_229/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_229/../traversal"));
    }
    @Test
    public void testPathSafetyCase_230() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_230/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_230/../traversal"));
    }
    @Test
    public void testPathSafetyCase_231() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_231/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_231/../traversal"));
    }
    @Test
    public void testPathSafetyCase_232() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_232/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_232/../traversal"));
    }
    @Test
    public void testPathSafetyCase_233() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_233/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_233/../traversal"));
    }
    @Test
    public void testPathSafetyCase_234() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_234/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_234/../traversal"));
    }
    @Test
    public void testPathSafetyCase_235() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_235/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_235/../traversal"));
    }
    @Test
    public void testPathSafetyCase_236() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_236/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_236/../traversal"));
    }
    @Test
    public void testPathSafetyCase_237() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_237/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_237/../traversal"));
    }
    @Test
    public void testPathSafetyCase_238() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_238/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_238/../traversal"));
    }
    @Test
    public void testPathSafetyCase_239() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_239/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_239/../traversal"));
    }
    @Test
    public void testPathSafetyCase_240() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_240/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_240/../traversal"));
    }
    @Test
    public void testPathSafetyCase_241() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_241/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_241/../traversal"));
    }
    @Test
    public void testPathSafetyCase_242() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_242/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_242/../traversal"));
    }
    @Test
    public void testPathSafetyCase_243() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_243/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_243/../traversal"));
    }
    @Test
    public void testPathSafetyCase_244() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_244/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_244/../traversal"));
    }
    @Test
    public void testPathSafetyCase_245() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_245/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_245/../traversal"));
    }
    @Test
    public void testPathSafetyCase_246() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_246/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_246/../traversal"));
    }
    @Test
    public void testPathSafetyCase_247() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_247/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_247/../traversal"));
    }
    @Test
    public void testPathSafetyCase_248() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_248/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_248/../traversal"));
    }
    @Test
    public void testPathSafetyCase_249() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_249/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_249/../traversal"));
    }
    @Test
    public void testPathSafetyCase_250() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_250/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_250/../traversal"));
    }
    @Test
    public void testPathSafetyCase_251() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_251/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_251/../traversal"));
    }
    @Test
    public void testPathSafetyCase_252() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_252/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_252/../traversal"));
    }
    @Test
    public void testPathSafetyCase_253() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_253/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_253/../traversal"));
    }
    @Test
    public void testPathSafetyCase_254() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_254/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_254/../traversal"));
    }
    @Test
    public void testPathSafetyCase_255() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_255/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_255/../traversal"));
    }
    @Test
    public void testPathSafetyCase_256() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_256/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_256/../traversal"));
    }
    @Test
    public void testPathSafetyCase_257() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_257/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_257/../traversal"));
    }
    @Test
    public void testPathSafetyCase_258() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_258/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_258/../traversal"));
    }
    @Test
    public void testPathSafetyCase_259() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_259/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_259/../traversal"));
    }
    @Test
    public void testPathSafetyCase_260() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_260/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_260/../traversal"));
    }
    @Test
    public void testPathSafetyCase_261() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_261/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_261/../traversal"));
    }
    @Test
    public void testPathSafetyCase_262() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_262/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_262/../traversal"));
    }
    @Test
    public void testPathSafetyCase_263() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_263/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_263/../traversal"));
    }
    @Test
    public void testPathSafetyCase_264() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_264/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_264/../traversal"));
    }
    @Test
    public void testPathSafetyCase_265() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_265/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_265/../traversal"));
    }
    @Test
    public void testPathSafetyCase_266() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_266/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_266/../traversal"));
    }
    @Test
    public void testPathSafetyCase_267() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_267/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_267/../traversal"));
    }
    @Test
    public void testPathSafetyCase_268() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_268/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_268/../traversal"));
    }
    @Test
    public void testPathSafetyCase_269() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_269/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_269/../traversal"));
    }
    @Test
    public void testPathSafetyCase_270() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_270/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_270/../traversal"));
    }
    @Test
    public void testPathSafetyCase_271() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_271/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_271/../traversal"));
    }
    @Test
    public void testPathSafetyCase_272() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_272/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_272/../traversal"));
    }
    @Test
    public void testPathSafetyCase_273() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_273/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_273/../traversal"));
    }
    @Test
    public void testPathSafetyCase_274() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_274/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_274/../traversal"));
    }
    @Test
    public void testPathSafetyCase_275() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_275/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_275/../traversal"));
    }
    @Test
    public void testPathSafetyCase_276() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_276/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_276/../traversal"));
    }
    @Test
    public void testPathSafetyCase_277() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_277/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_277/../traversal"));
    }
    @Test
    public void testPathSafetyCase_278() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_278/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_278/../traversal"));
    }
    @Test
    public void testPathSafetyCase_279() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_279/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_279/../traversal"));
    }
    @Test
    public void testPathSafetyCase_280() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_280/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_280/../traversal"));
    }
    @Test
    public void testPathSafetyCase_281() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_281/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_281/../traversal"));
    }
    @Test
    public void testPathSafetyCase_282() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_282/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_282/../traversal"));
    }
    @Test
    public void testPathSafetyCase_283() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_283/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_283/../traversal"));
    }
    @Test
    public void testPathSafetyCase_284() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_284/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_284/../traversal"));
    }
    @Test
    public void testPathSafetyCase_285() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_285/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_285/../traversal"));
    }
    @Test
    public void testPathSafetyCase_286() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_286/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_286/../traversal"));
    }
    @Test
    public void testPathSafetyCase_287() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_287/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_287/../traversal"));
    }
    @Test
    public void testPathSafetyCase_288() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_288/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_288/../traversal"));
    }
    @Test
    public void testPathSafetyCase_289() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_289/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_289/../traversal"));
    }
    @Test
    public void testPathSafetyCase_290() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_290/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_290/../traversal"));
    }
    @Test
    public void testPathSafetyCase_291() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_291/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_291/../traversal"));
    }
    @Test
    public void testPathSafetyCase_292() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_292/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_292/../traversal"));
    }
    @Test
    public void testPathSafetyCase_293() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_293/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_293/../traversal"));
    }
    @Test
    public void testPathSafetyCase_294() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_294/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_294/../traversal"));
    }
    @Test
    public void testPathSafetyCase_295() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_295/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_295/../traversal"));
    }
    @Test
    public void testPathSafetyCase_296() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_296/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_296/../traversal"));
    }
    @Test
    public void testPathSafetyCase_297() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_297/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_297/../traversal"));
    }
    @Test
    public void testPathSafetyCase_298() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_298/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_298/../traversal"));
    }
    @Test
    public void testPathSafetyCase_299() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_299/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_299/../traversal"));
    }
    @Test
    public void testPathSafetyCase_300() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_300/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_300/../traversal"));
    }
    @Test
    public void testPathSafetyCase_301() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_301/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_301/../traversal"));
    }
    @Test
    public void testPathSafetyCase_302() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_302/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_302/../traversal"));
    }
    @Test
    public void testPathSafetyCase_303() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_303/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_303/../traversal"));
    }
    @Test
    public void testPathSafetyCase_304() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_304/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_304/../traversal"));
    }
    @Test
    public void testPathSafetyCase_305() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_305/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_305/../traversal"));
    }
    @Test
    public void testPathSafetyCase_306() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_306/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_306/../traversal"));
    }
    @Test
    public void testPathSafetyCase_307() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_307/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_307/../traversal"));
    }
    @Test
    public void testPathSafetyCase_308() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_308/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_308/../traversal"));
    }
    @Test
    public void testPathSafetyCase_309() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_309/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_309/../traversal"));
    }
    @Test
    public void testPathSafetyCase_310() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_310/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_310/../traversal"));
    }
    @Test
    public void testPathSafetyCase_311() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_311/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_311/../traversal"));
    }
    @Test
    public void testPathSafetyCase_312() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_312/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_312/../traversal"));
    }
    @Test
    public void testPathSafetyCase_313() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_313/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_313/../traversal"));
    }
    @Test
    public void testPathSafetyCase_314() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_314/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_314/../traversal"));
    }
    @Test
    public void testPathSafetyCase_315() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_315/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_315/../traversal"));
    }
    @Test
    public void testPathSafetyCase_316() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_316/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_316/../traversal"));
    }
    @Test
    public void testPathSafetyCase_317() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_317/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_317/../traversal"));
    }
    @Test
    public void testPathSafetyCase_318() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_318/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_318/../traversal"));
    }
    @Test
    public void testPathSafetyCase_319() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_319/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_319/../traversal"));
    }
    @Test
    public void testPathSafetyCase_320() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_320/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_320/../traversal"));
    }
    @Test
    public void testPathSafetyCase_321() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_321/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_321/../traversal"));
    }
    @Test
    public void testPathSafetyCase_322() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_322/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_322/../traversal"));
    }
    @Test
    public void testPathSafetyCase_323() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_323/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_323/../traversal"));
    }
    @Test
    public void testPathSafetyCase_324() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_324/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_324/../traversal"));
    }
    @Test
    public void testPathSafetyCase_325() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_325/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_325/../traversal"));
    }
    @Test
    public void testPathSafetyCase_326() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_326/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_326/../traversal"));
    }
    @Test
    public void testPathSafetyCase_327() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_327/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_327/../traversal"));
    }
    @Test
    public void testPathSafetyCase_328() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_328/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_328/../traversal"));
    }
    @Test
    public void testPathSafetyCase_329() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_329/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_329/../traversal"));
    }
    @Test
    public void testPathSafetyCase_330() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_330/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_330/../traversal"));
    }
    @Test
    public void testPathSafetyCase_331() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_331/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_331/../traversal"));
    }
    @Test
    public void testPathSafetyCase_332() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_332/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_332/../traversal"));
    }
    @Test
    public void testPathSafetyCase_333() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_333/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_333/../traversal"));
    }
    @Test
    public void testPathSafetyCase_334() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_334/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_334/../traversal"));
    }
    @Test
    public void testPathSafetyCase_335() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_335/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_335/../traversal"));
    }
    @Test
    public void testPathSafetyCase_336() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_336/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_336/../traversal"));
    }
    @Test
    public void testPathSafetyCase_337() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_337/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_337/../traversal"));
    }
    @Test
    public void testPathSafetyCase_338() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_338/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_338/../traversal"));
    }
    @Test
    public void testPathSafetyCase_339() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_339/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_339/../traversal"));
    }
    @Test
    public void testPathSafetyCase_340() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_340/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_340/../traversal"));
    }
    @Test
    public void testPathSafetyCase_341() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_341/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_341/../traversal"));
    }
    @Test
    public void testPathSafetyCase_342() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_342/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_342/../traversal"));
    }
    @Test
    public void testPathSafetyCase_343() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_343/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_343/../traversal"));
    }
    @Test
    public void testPathSafetyCase_344() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_344/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_344/../traversal"));
    }
    @Test
    public void testPathSafetyCase_345() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_345/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_345/../traversal"));
    }
    @Test
    public void testPathSafetyCase_346() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_346/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_346/../traversal"));
    }
    @Test
    public void testPathSafetyCase_347() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_347/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_347/../traversal"));
    }
    @Test
    public void testPathSafetyCase_348() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_348/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_348/../traversal"));
    }
    @Test
    public void testPathSafetyCase_349() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_349/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_349/../traversal"));
    }
    @Test
    public void testPathSafetyCase_350() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_350/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_350/../traversal"));
    }
    @Test
    public void testPathSafetyCase_351() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_351/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_351/../traversal"));
    }
    @Test
    public void testPathSafetyCase_352() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_352/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_352/../traversal"));
    }
    @Test
    public void testPathSafetyCase_353() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_353/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_353/../traversal"));
    }
    @Test
    public void testPathSafetyCase_354() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_354/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_354/../traversal"));
    }
    @Test
    public void testPathSafetyCase_355() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_355/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_355/../traversal"));
    }
    @Test
    public void testPathSafetyCase_356() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_356/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_356/../traversal"));
    }
    @Test
    public void testPathSafetyCase_357() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_357/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_357/../traversal"));
    }
    @Test
    public void testPathSafetyCase_358() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_358/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_358/../traversal"));
    }
    @Test
    public void testPathSafetyCase_359() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_359/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_359/../traversal"));
    }
    @Test
    public void testPathSafetyCase_360() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_360/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_360/../traversal"));
    }
    @Test
    public void testPathSafetyCase_361() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_361/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_361/../traversal"));
    }
    @Test
    public void testPathSafetyCase_362() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_362/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_362/../traversal"));
    }
    @Test
    public void testPathSafetyCase_363() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_363/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_363/../traversal"));
    }
    @Test
    public void testPathSafetyCase_364() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_364/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_364/../traversal"));
    }
    @Test
    public void testPathSafetyCase_365() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_365/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_365/../traversal"));
    }
    @Test
    public void testPathSafetyCase_366() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_366/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_366/../traversal"));
    }
    @Test
    public void testPathSafetyCase_367() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_367/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_367/../traversal"));
    }
    @Test
    public void testPathSafetyCase_368() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_368/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_368/../traversal"));
    }
    @Test
    public void testPathSafetyCase_369() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_369/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_369/../traversal"));
    }
    @Test
    public void testPathSafetyCase_370() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_370/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_370/../traversal"));
    }
    @Test
    public void testPathSafetyCase_371() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_371/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_371/../traversal"));
    }
    @Test
    public void testPathSafetyCase_372() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_372/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_372/../traversal"));
    }
    @Test
    public void testPathSafetyCase_373() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_373/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_373/../traversal"));
    }
    @Test
    public void testPathSafetyCase_374() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_374/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_374/../traversal"));
    }
    @Test
    public void testPathSafetyCase_375() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_375/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_375/../traversal"));
    }
    @Test
    public void testPathSafetyCase_376() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_376/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_376/../traversal"));
    }
    @Test
    public void testPathSafetyCase_377() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_377/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_377/../traversal"));
    }
    @Test
    public void testPathSafetyCase_378() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_378/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_378/../traversal"));
    }
    @Test
    public void testPathSafetyCase_379() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_379/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_379/../traversal"));
    }
    @Test
    public void testPathSafetyCase_380() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_380/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_380/../traversal"));
    }
    @Test
    public void testPathSafetyCase_381() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_381/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_381/../traversal"));
    }
    @Test
    public void testPathSafetyCase_382() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_382/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_382/../traversal"));
    }
    @Test
    public void testPathSafetyCase_383() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_383/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_383/../traversal"));
    }
    @Test
    public void testPathSafetyCase_384() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_384/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_384/../traversal"));
    }
    @Test
    public void testPathSafetyCase_385() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_385/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_385/../traversal"));
    }
    @Test
    public void testPathSafetyCase_386() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_386/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_386/../traversal"));
    }
    @Test
    public void testPathSafetyCase_387() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_387/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_387/../traversal"));
    }
    @Test
    public void testPathSafetyCase_388() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_388/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_388/../traversal"));
    }
    @Test
    public void testPathSafetyCase_389() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_389/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_389/../traversal"));
    }
    @Test
    public void testPathSafetyCase_390() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_390/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_390/../traversal"));
    }
    @Test
    public void testPathSafetyCase_391() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_391/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_391/../traversal"));
    }
    @Test
    public void testPathSafetyCase_392() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_392/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_392/../traversal"));
    }
    @Test
    public void testPathSafetyCase_393() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_393/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_393/../traversal"));
    }
    @Test
    public void testPathSafetyCase_394() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_394/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_394/../traversal"));
    }
    @Test
    public void testPathSafetyCase_395() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_395/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_395/../traversal"));
    }
    @Test
    public void testPathSafetyCase_396() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_396/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_396/../traversal"));
    }
    @Test
    public void testPathSafetyCase_397() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_397/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_397/../traversal"));
    }
    @Test
    public void testPathSafetyCase_398() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_398/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_398/../traversal"));
    }
    @Test
    public void testPathSafetyCase_399() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_399/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_399/../traversal"));
    }
    @Test
    public void testPathSafetyCase_400() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_400/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_400/../traversal"));
    }
    @Test
    public void testPathSafetyCase_401() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_401/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_401/../traversal"));
    }
    @Test
    public void testPathSafetyCase_402() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_402/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_402/../traversal"));
    }
    @Test
    public void testPathSafetyCase_403() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_403/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_403/../traversal"));
    }
    @Test
    public void testPathSafetyCase_404() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_404/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_404/../traversal"));
    }
    @Test
    public void testPathSafetyCase_405() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_405/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_405/../traversal"));
    }
    @Test
    public void testPathSafetyCase_406() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_406/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_406/../traversal"));
    }
    @Test
    public void testPathSafetyCase_407() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_407/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_407/../traversal"));
    }
    @Test
    public void testPathSafetyCase_408() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_408/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_408/../traversal"));
    }
    @Test
    public void testPathSafetyCase_409() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_409/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_409/../traversal"));
    }
    @Test
    public void testPathSafetyCase_410() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_410/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_410/../traversal"));
    }
    @Test
    public void testPathSafetyCase_411() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_411/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_411/../traversal"));
    }
    @Test
    public void testPathSafetyCase_412() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_412/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_412/../traversal"));
    }
    @Test
    public void testPathSafetyCase_413() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_413/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_413/../traversal"));
    }
    @Test
    public void testPathSafetyCase_414() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_414/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_414/../traversal"));
    }
    @Test
    public void testPathSafetyCase_415() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_415/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_415/../traversal"));
    }
    @Test
    public void testPathSafetyCase_416() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_416/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_416/../traversal"));
    }
    @Test
    public void testPathSafetyCase_417() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_417/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_417/../traversal"));
    }
    @Test
    public void testPathSafetyCase_418() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_418/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_418/../traversal"));
    }
    @Test
    public void testPathSafetyCase_419() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_419/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_419/../traversal"));
    }
    @Test
    public void testPathSafetyCase_420() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_420/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_420/../traversal"));
    }
    @Test
    public void testPathSafetyCase_421() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_421/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_421/../traversal"));
    }
    @Test
    public void testPathSafetyCase_422() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_422/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_422/../traversal"));
    }
    @Test
    public void testPathSafetyCase_423() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_423/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_423/../traversal"));
    }
    @Test
    public void testPathSafetyCase_424() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_424/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_424/../traversal"));
    }
    @Test
    public void testPathSafetyCase_425() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_425/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_425/../traversal"));
    }
    @Test
    public void testPathSafetyCase_426() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_426/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_426/../traversal"));
    }
    @Test
    public void testPathSafetyCase_427() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_427/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_427/../traversal"));
    }
    @Test
    public void testPathSafetyCase_428() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_428/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_428/../traversal"));
    }
    @Test
    public void testPathSafetyCase_429() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_429/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_429/../traversal"));
    }
    @Test
    public void testPathSafetyCase_430() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_430/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_430/../traversal"));
    }
    @Test
    public void testPathSafetyCase_431() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_431/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_431/../traversal"));
    }
    @Test
    public void testPathSafetyCase_432() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_432/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_432/../traversal"));
    }
    @Test
    public void testPathSafetyCase_433() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_433/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_433/../traversal"));
    }
    @Test
    public void testPathSafetyCase_434() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_434/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_434/../traversal"));
    }
    @Test
    public void testPathSafetyCase_435() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_435/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_435/../traversal"));
    }
    @Test
    public void testPathSafetyCase_436() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_436/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_436/../traversal"));
    }
    @Test
    public void testPathSafetyCase_437() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_437/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_437/../traversal"));
    }
    @Test
    public void testPathSafetyCase_438() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_438/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_438/../traversal"));
    }
    @Test
    public void testPathSafetyCase_439() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_439/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_439/../traversal"));
    }
    @Test
    public void testPathSafetyCase_440() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_440/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_440/../traversal"));
    }
    @Test
    public void testPathSafetyCase_441() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_441/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_441/../traversal"));
    }
    @Test
    public void testPathSafetyCase_442() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_442/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_442/../traversal"));
    }
    @Test
    public void testPathSafetyCase_443() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_443/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_443/../traversal"));
    }
    @Test
    public void testPathSafetyCase_444() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_444/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_444/../traversal"));
    }
    @Test
    public void testPathSafetyCase_445() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_445/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_445/../traversal"));
    }
    @Test
    public void testPathSafetyCase_446() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_446/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_446/../traversal"));
    }
    @Test
    public void testPathSafetyCase_447() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_447/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_447/../traversal"));
    }
    @Test
    public void testPathSafetyCase_448() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_448/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_448/../traversal"));
    }
    @Test
    public void testPathSafetyCase_449() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_449/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_449/../traversal"));
    }
    @Test
    public void testPathSafetyCase_450() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_450/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_450/../traversal"));
    }
    @Test
    public void testPathSafetyCase_451() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_451/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_451/../traversal"));
    }
    @Test
    public void testPathSafetyCase_452() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_452/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_452/../traversal"));
    }
    @Test
    public void testPathSafetyCase_453() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_453/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_453/../traversal"));
    }
    @Test
    public void testPathSafetyCase_454() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_454/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_454/../traversal"));
    }
    @Test
    public void testPathSafetyCase_455() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_455/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_455/../traversal"));
    }
    @Test
    public void testPathSafetyCase_456() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_456/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_456/../traversal"));
    }
    @Test
    public void testPathSafetyCase_457() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_457/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_457/../traversal"));
    }
    @Test
    public void testPathSafetyCase_458() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_458/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_458/../traversal"));
    }
    @Test
    public void testPathSafetyCase_459() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_459/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_459/../traversal"));
    }
    @Test
    public void testPathSafetyCase_460() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_460/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_460/../traversal"));
    }
    @Test
    public void testPathSafetyCase_461() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_461/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_461/../traversal"));
    }
    @Test
    public void testPathSafetyCase_462() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_462/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_462/../traversal"));
    }
    @Test
    public void testPathSafetyCase_463() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_463/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_463/../traversal"));
    }
    @Test
    public void testPathSafetyCase_464() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_464/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_464/../traversal"));
    }
    @Test
    public void testPathSafetyCase_465() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_465/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_465/../traversal"));
    }
    @Test
    public void testPathSafetyCase_466() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_466/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_466/../traversal"));
    }
    @Test
    public void testPathSafetyCase_467() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_467/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_467/../traversal"));
    }
    @Test
    public void testPathSafetyCase_468() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_468/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_468/../traversal"));
    }
    @Test
    public void testPathSafetyCase_469() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_469/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_469/../traversal"));
    }
    @Test
    public void testPathSafetyCase_470() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_470/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_470/../traversal"));
    }
    @Test
    public void testPathSafetyCase_471() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_471/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_471/../traversal"));
    }
    @Test
    public void testPathSafetyCase_472() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_472/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_472/../traversal"));
    }
    @Test
    public void testPathSafetyCase_473() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_473/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_473/../traversal"));
    }
    @Test
    public void testPathSafetyCase_474() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_474/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_474/../traversal"));
    }
    @Test
    public void testPathSafetyCase_475() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_475/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_475/../traversal"));
    }
    @Test
    public void testPathSafetyCase_476() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_476/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_476/../traversal"));
    }
    @Test
    public void testPathSafetyCase_477() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_477/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_477/../traversal"));
    }
    @Test
    public void testPathSafetyCase_478() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_478/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_478/../traversal"));
    }
    @Test
    public void testPathSafetyCase_479() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_479/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_479/../traversal"));
    }
    @Test
    public void testPathSafetyCase_480() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_480/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_480/../traversal"));
    }
    @Test
    public void testPathSafetyCase_481() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_481/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_481/../traversal"));
    }
    @Test
    public void testPathSafetyCase_482() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_482/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_482/../traversal"));
    }
    @Test
    public void testPathSafetyCase_483() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_483/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_483/../traversal"));
    }
    @Test
    public void testPathSafetyCase_484() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_484/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_484/../traversal"));
    }
    @Test
    public void testPathSafetyCase_485() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_485/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_485/../traversal"));
    }
    @Test
    public void testPathSafetyCase_486() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_486/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_486/../traversal"));
    }
    @Test
    public void testPathSafetyCase_487() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_487/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_487/../traversal"));
    }
    @Test
    public void testPathSafetyCase_488() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_488/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_488/../traversal"));
    }
    @Test
    public void testPathSafetyCase_489() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_489/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_489/../traversal"));
    }
    @Test
    public void testPathSafetyCase_490() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_490/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_490/../traversal"));
    }
    @Test
    public void testPathSafetyCase_491() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_491/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_491/../traversal"));
    }
    @Test
    public void testPathSafetyCase_492() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_492/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_492/../traversal"));
    }
    @Test
    public void testPathSafetyCase_493() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_493/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_493/../traversal"));
    }
    @Test
    public void testPathSafetyCase_494() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_494/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_494/../traversal"));
    }
    @Test
    public void testPathSafetyCase_495() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_495/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_495/../traversal"));
    }
    @Test
    public void testPathSafetyCase_496() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_496/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_496/../traversal"));
    }
    @Test
    public void testPathSafetyCase_497() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_497/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_497/../traversal"));
    }
    @Test
    public void testPathSafetyCase_498() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_498/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_498/../traversal"));
    }
    @Test
    public void testPathSafetyCase_499() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_499/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_499/../traversal"));
    }
    @Test
    public void testPathSafetyCase_500() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_500/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_500/../traversal"));
    }
    @Test
    public void testPathSafetyCase_501() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_501/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_501/../traversal"));
    }
    @Test
    public void testPathSafetyCase_502() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_502/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_502/../traversal"));
    }
    @Test
    public void testPathSafetyCase_503() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_503/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_503/../traversal"));
    }
    @Test
    public void testPathSafetyCase_504() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_504/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_504/../traversal"));
    }
    @Test
    public void testPathSafetyCase_505() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_505/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_505/../traversal"));
    }
    @Test
    public void testPathSafetyCase_506() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_506/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_506/../traversal"));
    }
    @Test
    public void testPathSafetyCase_507() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_507/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_507/../traversal"));
    }
    @Test
    public void testPathSafetyCase_508() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_508/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_508/../traversal"));
    }
    @Test
    public void testPathSafetyCase_509() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_509/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_509/../traversal"));
    }
    @Test
    public void testPathSafetyCase_510() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_510/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_510/../traversal"));
    }
    @Test
    public void testPathSafetyCase_511() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_511/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_511/../traversal"));
    }
    @Test
    public void testPathSafetyCase_512() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_512/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_512/../traversal"));
    }
    @Test
    public void testPathSafetyCase_513() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_513/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_513/../traversal"));
    }
    @Test
    public void testPathSafetyCase_514() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_514/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_514/../traversal"));
    }
    @Test
    public void testPathSafetyCase_515() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_515/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_515/../traversal"));
    }
    @Test
    public void testPathSafetyCase_516() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_516/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_516/../traversal"));
    }
    @Test
    public void testPathSafetyCase_517() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_517/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_517/../traversal"));
    }
    @Test
    public void testPathSafetyCase_518() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_518/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_518/../traversal"));
    }
    @Test
    public void testPathSafetyCase_519() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_519/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_519/../traversal"));
    }
    @Test
    public void testPathSafetyCase_520() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_520/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_520/../traversal"));
    }
    @Test
    public void testPathSafetyCase_521() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_521/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_521/../traversal"));
    }
    @Test
    public void testPathSafetyCase_522() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_522/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_522/../traversal"));
    }
    @Test
    public void testPathSafetyCase_523() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_523/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_523/../traversal"));
    }
    @Test
    public void testPathSafetyCase_524() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_524/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_524/../traversal"));
    }
    @Test
    public void testPathSafetyCase_525() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_525/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_525/../traversal"));
    }
    @Test
    public void testPathSafetyCase_526() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_526/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_526/../traversal"));
    }
    @Test
    public void testPathSafetyCase_527() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_527/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_527/../traversal"));
    }
    @Test
    public void testPathSafetyCase_528() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_528/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_528/../traversal"));
    }
    @Test
    public void testPathSafetyCase_529() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_529/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_529/../traversal"));
    }
    @Test
    public void testPathSafetyCase_530() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_530/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_530/../traversal"));
    }
    @Test
    public void testPathSafetyCase_531() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_531/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_531/../traversal"));
    }
    @Test
    public void testPathSafetyCase_532() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_532/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_532/../traversal"));
    }
    @Test
    public void testPathSafetyCase_533() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_533/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_533/../traversal"));
    }
    @Test
    public void testPathSafetyCase_534() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_534/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_534/../traversal"));
    }
    @Test
    public void testPathSafetyCase_535() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_535/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_535/../traversal"));
    }
    @Test
    public void testPathSafetyCase_536() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_536/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_536/../traversal"));
    }
    @Test
    public void testPathSafetyCase_537() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_537/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_537/../traversal"));
    }
    @Test
    public void testPathSafetyCase_538() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_538/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_538/../traversal"));
    }
    @Test
    public void testPathSafetyCase_539() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_539/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_539/../traversal"));
    }
    @Test
    public void testPathSafetyCase_540() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_540/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_540/../traversal"));
    }
    @Test
    public void testPathSafetyCase_541() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_541/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_541/../traversal"));
    }
    @Test
    public void testPathSafetyCase_542() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_542/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_542/../traversal"));
    }
    @Test
    public void testPathSafetyCase_543() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_543/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_543/../traversal"));
    }
    @Test
    public void testPathSafetyCase_544() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_544/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_544/../traversal"));
    }
    @Test
    public void testPathSafetyCase_545() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_545/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_545/../traversal"));
    }
    @Test
    public void testPathSafetyCase_546() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_546/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_546/../traversal"));
    }
    @Test
    public void testPathSafetyCase_547() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_547/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_547/../traversal"));
    }
    @Test
    public void testPathSafetyCase_548() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_548/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_548/../traversal"));
    }
    @Test
    public void testPathSafetyCase_549() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_549/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_549/../traversal"));
    }
    @Test
    public void testPathSafetyCase_550() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_550/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_550/../traversal"));
    }
    @Test
    public void testPathSafetyCase_551() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_551/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_551/../traversal"));
    }
    @Test
    public void testPathSafetyCase_552() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_552/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_552/../traversal"));
    }
    @Test
    public void testPathSafetyCase_553() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_553/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_553/../traversal"));
    }
    @Test
    public void testPathSafetyCase_554() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_554/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_554/../traversal"));
    }
    @Test
    public void testPathSafetyCase_555() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_555/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_555/../traversal"));
    }
    @Test
    public void testPathSafetyCase_556() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_556/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_556/../traversal"));
    }
    @Test
    public void testPathSafetyCase_557() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_557/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_557/../traversal"));
    }
    @Test
    public void testPathSafetyCase_558() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_558/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_558/../traversal"));
    }
    @Test
    public void testPathSafetyCase_559() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_559/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_559/../traversal"));
    }
    @Test
    public void testPathSafetyCase_560() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_560/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_560/../traversal"));
    }
    @Test
    public void testPathSafetyCase_561() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_561/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_561/../traversal"));
    }
    @Test
    public void testPathSafetyCase_562() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_562/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_562/../traversal"));
    }
    @Test
    public void testPathSafetyCase_563() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_563/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_563/../traversal"));
    }
    @Test
    public void testPathSafetyCase_564() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_564/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_564/../traversal"));
    }
    @Test
    public void testPathSafetyCase_565() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_565/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_565/../traversal"));
    }
    @Test
    public void testPathSafetyCase_566() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_566/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_566/../traversal"));
    }
    @Test
    public void testPathSafetyCase_567() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_567/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_567/../traversal"));
    }
    @Test
    public void testPathSafetyCase_568() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_568/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_568/../traversal"));
    }
    @Test
    public void testPathSafetyCase_569() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_569/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_569/../traversal"));
    }
    @Test
    public void testPathSafetyCase_570() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_570/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_570/../traversal"));
    }
    @Test
    public void testPathSafetyCase_571() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_571/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_571/../traversal"));
    }
    @Test
    public void testPathSafetyCase_572() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_572/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_572/../traversal"));
    }
    @Test
    public void testPathSafetyCase_573() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_573/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_573/../traversal"));
    }
    @Test
    public void testPathSafetyCase_574() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_574/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_574/../traversal"));
    }
    @Test
    public void testPathSafetyCase_575() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_575/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_575/../traversal"));
    }
    @Test
    public void testPathSafetyCase_576() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_576/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_576/../traversal"));
    }
    @Test
    public void testPathSafetyCase_577() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_577/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_577/../traversal"));
    }
    @Test
    public void testPathSafetyCase_578() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_578/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_578/../traversal"));
    }
    @Test
    public void testPathSafetyCase_579() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_579/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_579/../traversal"));
    }
    @Test
    public void testPathSafetyCase_580() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_580/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_580/../traversal"));
    }
    @Test
    public void testPathSafetyCase_581() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_581/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_581/../traversal"));
    }
    @Test
    public void testPathSafetyCase_582() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_582/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_582/../traversal"));
    }
    @Test
    public void testPathSafetyCase_583() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_583/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_583/../traversal"));
    }
    @Test
    public void testPathSafetyCase_584() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_584/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_584/../traversal"));
    }
    @Test
    public void testPathSafetyCase_585() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_585/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_585/../traversal"));
    }
    @Test
    public void testPathSafetyCase_586() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_586/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_586/../traversal"));
    }
    @Test
    public void testPathSafetyCase_587() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_587/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_587/../traversal"));
    }
    @Test
    public void testPathSafetyCase_588() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_588/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_588/../traversal"));
    }
    @Test
    public void testPathSafetyCase_589() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_589/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_589/../traversal"));
    }
    @Test
    public void testPathSafetyCase_590() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_590/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_590/../traversal"));
    }
    @Test
    public void testPathSafetyCase_591() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_591/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_591/../traversal"));
    }
    @Test
    public void testPathSafetyCase_592() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_592/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_592/../traversal"));
    }
    @Test
    public void testPathSafetyCase_593() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_593/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_593/../traversal"));
    }
    @Test
    public void testPathSafetyCase_594() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_594/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_594/../traversal"));
    }
    @Test
    public void testPathSafetyCase_595() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_595/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_595/../traversal"));
    }
    @Test
    public void testPathSafetyCase_596() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_596/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_596/../traversal"));
    }
    @Test
    public void testPathSafetyCase_597() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_597/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_597/../traversal"));
    }
    @Test
    public void testPathSafetyCase_598() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_598/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_598/../traversal"));
    }
    @Test
    public void testPathSafetyCase_599() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_599/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_599/../traversal"));
    }
    @Test
    public void testPathSafetyCase_600() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_600/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_600/../traversal"));
    }
    @Test
    public void testPathSafetyCase_601() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_601/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_601/../traversal"));
    }
    @Test
    public void testPathSafetyCase_602() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_602/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_602/../traversal"));
    }
    @Test
    public void testPathSafetyCase_603() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_603/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_603/../traversal"));
    }
    @Test
    public void testPathSafetyCase_604() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_604/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_604/../traversal"));
    }
    @Test
    public void testPathSafetyCase_605() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_605/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_605/../traversal"));
    }
    @Test
    public void testPathSafetyCase_606() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_606/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_606/../traversal"));
    }
    @Test
    public void testPathSafetyCase_607() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_607/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_607/../traversal"));
    }
    @Test
    public void testPathSafetyCase_608() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_608/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_608/../traversal"));
    }
    @Test
    public void testPathSafetyCase_609() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_609/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_609/../traversal"));
    }
    @Test
    public void testPathSafetyCase_610() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_610/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_610/../traversal"));
    }
    @Test
    public void testPathSafetyCase_611() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_611/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_611/../traversal"));
    }
    @Test
    public void testPathSafetyCase_612() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_612/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_612/../traversal"));
    }
    @Test
    public void testPathSafetyCase_613() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_613/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_613/../traversal"));
    }
    @Test
    public void testPathSafetyCase_614() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_614/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_614/../traversal"));
    }
    @Test
    public void testPathSafetyCase_615() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_615/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_615/../traversal"));
    }
    @Test
    public void testPathSafetyCase_616() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_616/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_616/../traversal"));
    }
    @Test
    public void testPathSafetyCase_617() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_617/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_617/../traversal"));
    }
    @Test
    public void testPathSafetyCase_618() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_618/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_618/../traversal"));
    }
    @Test
    public void testPathSafetyCase_619() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_619/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_619/../traversal"));
    }
    @Test
    public void testPathSafetyCase_620() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_620/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_620/../traversal"));
    }
    @Test
    public void testPathSafetyCase_621() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_621/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_621/../traversal"));
    }
    @Test
    public void testPathSafetyCase_622() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_622/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_622/../traversal"));
    }
    @Test
    public void testPathSafetyCase_623() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_623/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_623/../traversal"));
    }
    @Test
    public void testPathSafetyCase_624() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_624/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_624/../traversal"));
    }
    @Test
    public void testPathSafetyCase_625() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_625/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_625/../traversal"));
    }
    @Test
    public void testPathSafetyCase_626() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_626/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_626/../traversal"));
    }
    @Test
    public void testPathSafetyCase_627() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_627/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_627/../traversal"));
    }
    @Test
    public void testPathSafetyCase_628() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_628/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_628/../traversal"));
    }
    @Test
    public void testPathSafetyCase_629() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_629/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_629/../traversal"));
    }
    @Test
    public void testPathSafetyCase_630() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_630/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_630/../traversal"));
    }
    @Test
    public void testPathSafetyCase_631() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_631/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_631/../traversal"));
    }
    @Test
    public void testPathSafetyCase_632() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_632/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_632/../traversal"));
    }
    @Test
    public void testPathSafetyCase_633() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_633/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_633/../traversal"));
    }
    @Test
    public void testPathSafetyCase_634() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_634/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_634/../traversal"));
    }
    @Test
    public void testPathSafetyCase_635() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_635/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_635/../traversal"));
    }
    @Test
    public void testPathSafetyCase_636() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_636/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_636/../traversal"));
    }
    @Test
    public void testPathSafetyCase_637() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_637/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_637/../traversal"));
    }
    @Test
    public void testPathSafetyCase_638() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_638/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_638/../traversal"));
    }
    @Test
    public void testPathSafetyCase_639() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_639/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_639/../traversal"));
    }
    @Test
    public void testPathSafetyCase_640() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_640/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_640/../traversal"));
    }
    @Test
    public void testPathSafetyCase_641() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_641/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_641/../traversal"));
    }
    @Test
    public void testPathSafetyCase_642() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_642/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_642/../traversal"));
    }
    @Test
    public void testPathSafetyCase_643() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_643/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_643/../traversal"));
    }
    @Test
    public void testPathSafetyCase_644() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_644/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_644/../traversal"));
    }
    @Test
    public void testPathSafetyCase_645() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_645/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_645/../traversal"));
    }
    @Test
    public void testPathSafetyCase_646() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_646/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_646/../traversal"));
    }
    @Test
    public void testPathSafetyCase_647() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_647/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_647/../traversal"));
    }
    @Test
    public void testPathSafetyCase_648() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_648/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_648/../traversal"));
    }
    @Test
    public void testPathSafetyCase_649() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_649/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_649/../traversal"));
    }
    @Test
    public void testPathSafetyCase_650() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_650/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_650/../traversal"));
    }
    @Test
    public void testPathSafetyCase_651() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_651/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_651/../traversal"));
    }
    @Test
    public void testPathSafetyCase_652() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_652/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_652/../traversal"));
    }
    @Test
    public void testPathSafetyCase_653() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_653/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_653/../traversal"));
    }
    @Test
    public void testPathSafetyCase_654() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_654/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_654/../traversal"));
    }
    @Test
    public void testPathSafetyCase_655() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_655/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_655/../traversal"));
    }
    @Test
    public void testPathSafetyCase_656() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_656/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_656/../traversal"));
    }
    @Test
    public void testPathSafetyCase_657() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_657/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_657/../traversal"));
    }
    @Test
    public void testPathSafetyCase_658() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_658/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_658/../traversal"));
    }
    @Test
    public void testPathSafetyCase_659() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_659/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_659/../traversal"));
    }
    @Test
    public void testPathSafetyCase_660() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_660/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_660/../traversal"));
    }
    @Test
    public void testPathSafetyCase_661() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_661/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_661/../traversal"));
    }
    @Test
    public void testPathSafetyCase_662() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_662/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_662/../traversal"));
    }
    @Test
    public void testPathSafetyCase_663() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_663/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_663/../traversal"));
    }
    @Test
    public void testPathSafetyCase_664() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_664/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_664/../traversal"));
    }
    @Test
    public void testPathSafetyCase_665() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_665/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_665/../traversal"));
    }
    @Test
    public void testPathSafetyCase_666() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_666/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_666/../traversal"));
    }
    @Test
    public void testPathSafetyCase_667() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_667/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_667/../traversal"));
    }
    @Test
    public void testPathSafetyCase_668() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_668/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_668/../traversal"));
    }
    @Test
    public void testPathSafetyCase_669() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_669/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_669/../traversal"));
    }
    @Test
    public void testPathSafetyCase_670() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_670/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_670/../traversal"));
    }
    @Test
    public void testPathSafetyCase_671() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_671/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_671/../traversal"));
    }
    @Test
    public void testPathSafetyCase_672() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_672/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_672/../traversal"));
    }
    @Test
    public void testPathSafetyCase_673() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_673/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_673/../traversal"));
    }
    @Test
    public void testPathSafetyCase_674() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_674/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_674/../traversal"));
    }
    @Test
    public void testPathSafetyCase_675() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_675/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_675/../traversal"));
    }
    @Test
    public void testPathSafetyCase_676() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_676/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_676/../traversal"));
    }
    @Test
    public void testPathSafetyCase_677() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_677/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_677/../traversal"));
    }
    @Test
    public void testPathSafetyCase_678() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_678/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_678/../traversal"));
    }
    @Test
    public void testPathSafetyCase_679() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_679/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_679/../traversal"));
    }
    @Test
    public void testPathSafetyCase_680() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_680/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_680/../traversal"));
    }
    @Test
    public void testPathSafetyCase_681() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_681/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_681/../traversal"));
    }
    @Test
    public void testPathSafetyCase_682() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_682/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_682/../traversal"));
    }
    @Test
    public void testPathSafetyCase_683() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_683/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_683/../traversal"));
    }
    @Test
    public void testPathSafetyCase_684() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_684/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_684/../traversal"));
    }
    @Test
    public void testPathSafetyCase_685() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_685/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_685/../traversal"));
    }
    @Test
    public void testPathSafetyCase_686() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_686/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_686/../traversal"));
    }
    @Test
    public void testPathSafetyCase_687() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_687/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_687/../traversal"));
    }
    @Test
    public void testPathSafetyCase_688() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_688/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_688/../traversal"));
    }
    @Test
    public void testPathSafetyCase_689() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_689/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_689/../traversal"));
    }
    @Test
    public void testPathSafetyCase_690() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_690/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_690/../traversal"));
    }
    @Test
    public void testPathSafetyCase_691() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_691/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_691/../traversal"));
    }
    @Test
    public void testPathSafetyCase_692() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_692/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_692/../traversal"));
    }
    @Test
    public void testPathSafetyCase_693() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_693/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_693/../traversal"));
    }
    @Test
    public void testPathSafetyCase_694() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_694/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_694/../traversal"));
    }
    @Test
    public void testPathSafetyCase_695() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_695/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_695/../traversal"));
    }
    @Test
    public void testPathSafetyCase_696() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_696/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_696/../traversal"));
    }
    @Test
    public void testPathSafetyCase_697() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_697/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_697/../traversal"));
    }
    @Test
    public void testPathSafetyCase_698() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_698/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_698/../traversal"));
    }
    @Test
    public void testPathSafetyCase_699() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_699/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_699/../traversal"));
    }
    @Test
    public void testPathSafetyCase_700() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_700/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_700/../traversal"));
    }
    @Test
    public void testPathSafetyCase_701() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_701/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_701/../traversal"));
    }
    @Test
    public void testPathSafetyCase_702() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_702/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_702/../traversal"));
    }
    @Test
    public void testPathSafetyCase_703() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_703/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_703/../traversal"));
    }
    @Test
    public void testPathSafetyCase_704() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_704/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_704/../traversal"));
    }
    @Test
    public void testPathSafetyCase_705() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_705/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_705/../traversal"));
    }
    @Test
    public void testPathSafetyCase_706() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_706/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_706/../traversal"));
    }
    @Test
    public void testPathSafetyCase_707() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_707/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_707/../traversal"));
    }
    @Test
    public void testPathSafetyCase_708() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_708/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_708/../traversal"));
    }
    @Test
    public void testPathSafetyCase_709() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_709/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_709/../traversal"));
    }
    @Test
    public void testPathSafetyCase_710() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_710/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_710/../traversal"));
    }
    @Test
    public void testPathSafetyCase_711() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_711/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_711/../traversal"));
    }
    @Test
    public void testPathSafetyCase_712() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_712/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_712/../traversal"));
    }
    @Test
    public void testPathSafetyCase_713() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_713/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_713/../traversal"));
    }
    @Test
    public void testPathSafetyCase_714() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_714/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_714/../traversal"));
    }
    @Test
    public void testPathSafetyCase_715() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_715/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_715/../traversal"));
    }
    @Test
    public void testPathSafetyCase_716() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_716/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_716/../traversal"));
    }
    @Test
    public void testPathSafetyCase_717() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_717/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_717/../traversal"));
    }
    @Test
    public void testPathSafetyCase_718() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_718/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_718/../traversal"));
    }
    @Test
    public void testPathSafetyCase_719() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_719/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_719/../traversal"));
    }
    @Test
    public void testPathSafetyCase_720() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_720/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_720/../traversal"));
    }
    @Test
    public void testPathSafetyCase_721() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_721/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_721/../traversal"));
    }
    @Test
    public void testPathSafetyCase_722() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_722/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_722/../traversal"));
    }
    @Test
    public void testPathSafetyCase_723() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_723/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_723/../traversal"));
    }
    @Test
    public void testPathSafetyCase_724() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_724/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_724/../traversal"));
    }
    @Test
    public void testPathSafetyCase_725() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_725/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_725/../traversal"));
    }
    @Test
    public void testPathSafetyCase_726() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_726/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_726/../traversal"));
    }
    @Test
    public void testPathSafetyCase_727() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_727/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_727/../traversal"));
    }
    @Test
    public void testPathSafetyCase_728() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_728/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_728/../traversal"));
    }
    @Test
    public void testPathSafetyCase_729() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_729/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_729/../traversal"));
    }
    @Test
    public void testPathSafetyCase_730() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_730/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_730/../traversal"));
    }
    @Test
    public void testPathSafetyCase_731() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_731/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_731/../traversal"));
    }
    @Test
    public void testPathSafetyCase_732() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_732/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_732/../traversal"));
    }
    @Test
    public void testPathSafetyCase_733() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_733/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_733/../traversal"));
    }
    @Test
    public void testPathSafetyCase_734() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_734/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_734/../traversal"));
    }
    @Test
    public void testPathSafetyCase_735() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_735/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_735/../traversal"));
    }
    @Test
    public void testPathSafetyCase_736() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_736/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_736/../traversal"));
    }
    @Test
    public void testPathSafetyCase_737() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_737/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_737/../traversal"));
    }
    @Test
    public void testPathSafetyCase_738() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_738/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_738/../traversal"));
    }
    @Test
    public void testPathSafetyCase_739() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_739/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_739/../traversal"));
    }
    @Test
    public void testPathSafetyCase_740() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_740/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_740/../traversal"));
    }
    @Test
    public void testPathSafetyCase_741() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_741/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_741/../traversal"));
    }
    @Test
    public void testPathSafetyCase_742() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_742/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_742/../traversal"));
    }
    @Test
    public void testPathSafetyCase_743() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_743/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_743/../traversal"));
    }
    @Test
    public void testPathSafetyCase_744() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_744/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_744/../traversal"));
    }
    @Test
    public void testPathSafetyCase_745() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_745/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_745/../traversal"));
    }
    @Test
    public void testPathSafetyCase_746() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_746/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_746/../traversal"));
    }
    @Test
    public void testPathSafetyCase_747() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_747/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_747/../traversal"));
    }
    @Test
    public void testPathSafetyCase_748() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_748/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_748/../traversal"));
    }
    @Test
    public void testPathSafetyCase_749() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_749/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_749/../traversal"));
    }
    @Test
    public void testPathSafetyCase_750() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_750/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_750/../traversal"));
    }
    @Test
    public void testPathSafetyCase_751() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_751/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_751/../traversal"));
    }
    @Test
    public void testPathSafetyCase_752() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_752/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_752/../traversal"));
    }
    @Test
    public void testPathSafetyCase_753() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_753/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_753/../traversal"));
    }
    @Test
    public void testPathSafetyCase_754() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_754/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_754/../traversal"));
    }
    @Test
    public void testPathSafetyCase_755() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_755/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_755/../traversal"));
    }
    @Test
    public void testPathSafetyCase_756() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_756/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_756/../traversal"));
    }
    @Test
    public void testPathSafetyCase_757() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_757/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_757/../traversal"));
    }
    @Test
    public void testPathSafetyCase_758() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_758/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_758/../traversal"));
    }
    @Test
    public void testPathSafetyCase_759() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_759/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_759/../traversal"));
    }
    @Test
    public void testPathSafetyCase_760() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_760/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_760/../traversal"));
    }
    @Test
    public void testPathSafetyCase_761() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_761/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_761/../traversal"));
    }
    @Test
    public void testPathSafetyCase_762() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_762/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_762/../traversal"));
    }
    @Test
    public void testPathSafetyCase_763() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_763/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_763/../traversal"));
    }
    @Test
    public void testPathSafetyCase_764() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_764/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_764/../traversal"));
    }
    @Test
    public void testPathSafetyCase_765() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_765/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_765/../traversal"));
    }
    @Test
    public void testPathSafetyCase_766() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_766/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_766/../traversal"));
    }
    @Test
    public void testPathSafetyCase_767() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_767/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_767/../traversal"));
    }
    @Test
    public void testPathSafetyCase_768() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_768/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_768/../traversal"));
    }
    @Test
    public void testPathSafetyCase_769() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_769/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_769/../traversal"));
    }
    @Test
    public void testPathSafetyCase_770() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_770/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_770/../traversal"));
    }
    @Test
    public void testPathSafetyCase_771() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_771/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_771/../traversal"));
    }
    @Test
    public void testPathSafetyCase_772() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_772/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_772/../traversal"));
    }
    @Test
    public void testPathSafetyCase_773() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_773/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_773/../traversal"));
    }
    @Test
    public void testPathSafetyCase_774() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_774/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_774/../traversal"));
    }
    @Test
    public void testPathSafetyCase_775() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_775/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_775/../traversal"));
    }
    @Test
    public void testPathSafetyCase_776() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_776/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_776/../traversal"));
    }
    @Test
    public void testPathSafetyCase_777() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_777/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_777/../traversal"));
    }
    @Test
    public void testPathSafetyCase_778() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_778/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_778/../traversal"));
    }
    @Test
    public void testPathSafetyCase_779() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_779/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_779/../traversal"));
    }
    @Test
    public void testPathSafetyCase_780() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_780/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_780/../traversal"));
    }
    @Test
    public void testPathSafetyCase_781() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_781/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_781/../traversal"));
    }
    @Test
    public void testPathSafetyCase_782() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_782/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_782/../traversal"));
    }
    @Test
    public void testPathSafetyCase_783() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_783/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_783/../traversal"));
    }
    @Test
    public void testPathSafetyCase_784() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_784/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_784/../traversal"));
    }
    @Test
    public void testPathSafetyCase_785() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_785/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_785/../traversal"));
    }
    @Test
    public void testPathSafetyCase_786() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_786/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_786/../traversal"));
    }
    @Test
    public void testPathSafetyCase_787() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_787/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_787/../traversal"));
    }
    @Test
    public void testPathSafetyCase_788() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_788/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_788/../traversal"));
    }
    @Test
    public void testPathSafetyCase_789() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_789/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_789/../traversal"));
    }
    @Test
    public void testPathSafetyCase_790() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_790/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_790/../traversal"));
    }
    @Test
    public void testPathSafetyCase_791() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_791/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_791/../traversal"));
    }
    @Test
    public void testPathSafetyCase_792() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_792/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_792/../traversal"));
    }
    @Test
    public void testPathSafetyCase_793() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_793/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_793/../traversal"));
    }
    @Test
    public void testPathSafetyCase_794() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_794/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_794/../traversal"));
    }
    @Test
    public void testPathSafetyCase_795() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_795/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_795/../traversal"));
    }
    @Test
    public void testPathSafetyCase_796() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_796/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_796/../traversal"));
    }
    @Test
    public void testPathSafetyCase_797() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_797/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_797/../traversal"));
    }
    @Test
    public void testPathSafetyCase_798() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_798/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_798/../traversal"));
    }
    @Test
    public void testPathSafetyCase_799() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_799/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_799/../traversal"));
    }
    @Test
    public void testPathSafetyCase_800() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_800/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_800/../traversal"));
    }
    @Test
    public void testPathSafetyCase_801() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_801/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_801/../traversal"));
    }
    @Test
    public void testPathSafetyCase_802() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_802/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_802/../traversal"));
    }
    @Test
    public void testPathSafetyCase_803() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_803/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_803/../traversal"));
    }
    @Test
    public void testPathSafetyCase_804() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_804/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_804/../traversal"));
    }
    @Test
    public void testPathSafetyCase_805() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_805/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_805/../traversal"));
    }
    @Test
    public void testPathSafetyCase_806() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_806/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_806/../traversal"));
    }
    @Test
    public void testPathSafetyCase_807() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_807/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_807/../traversal"));
    }
    @Test
    public void testPathSafetyCase_808() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_808/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_808/../traversal"));
    }
    @Test
    public void testPathSafetyCase_809() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_809/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_809/../traversal"));
    }
    @Test
    public void testPathSafetyCase_810() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_810/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_810/../traversal"));
    }
    @Test
    public void testPathSafetyCase_811() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_811/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_811/../traversal"));
    }
    @Test
    public void testPathSafetyCase_812() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_812/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_812/../traversal"));
    }
    @Test
    public void testPathSafetyCase_813() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_813/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_813/../traversal"));
    }
    @Test
    public void testPathSafetyCase_814() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_814/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_814/../traversal"));
    }
    @Test
    public void testPathSafetyCase_815() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_815/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_815/../traversal"));
    }
    @Test
    public void testPathSafetyCase_816() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_816/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_816/../traversal"));
    }
    @Test
    public void testPathSafetyCase_817() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_817/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_817/../traversal"));
    }
    @Test
    public void testPathSafetyCase_818() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_818/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_818/../traversal"));
    }
    @Test
    public void testPathSafetyCase_819() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_819/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_819/../traversal"));
    }
    @Test
    public void testPathSafetyCase_820() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_820/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_820/../traversal"));
    }
    @Test
    public void testPathSafetyCase_821() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_821/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_821/../traversal"));
    }
    @Test
    public void testPathSafetyCase_822() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_822/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_822/../traversal"));
    }
    @Test
    public void testPathSafetyCase_823() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_823/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_823/../traversal"));
    }
    @Test
    public void testPathSafetyCase_824() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_824/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_824/../traversal"));
    }
    @Test
    public void testPathSafetyCase_825() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_825/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_825/../traversal"));
    }
    @Test
    public void testPathSafetyCase_826() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_826/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_826/../traversal"));
    }
    @Test
    public void testPathSafetyCase_827() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_827/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_827/../traversal"));
    }
    @Test
    public void testPathSafetyCase_828() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_828/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_828/../traversal"));
    }
    @Test
    public void testPathSafetyCase_829() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_829/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_829/../traversal"));
    }
    @Test
    public void testPathSafetyCase_830() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_830/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_830/../traversal"));
    }
    @Test
    public void testPathSafetyCase_831() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_831/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_831/../traversal"));
    }
    @Test
    public void testPathSafetyCase_832() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_832/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_832/../traversal"));
    }
    @Test
    public void testPathSafetyCase_833() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_833/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_833/../traversal"));
    }
    @Test
    public void testPathSafetyCase_834() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_834/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_834/../traversal"));
    }
    @Test
    public void testPathSafetyCase_835() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_835/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_835/../traversal"));
    }
    @Test
    public void testPathSafetyCase_836() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_836/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_836/../traversal"));
    }
    @Test
    public void testPathSafetyCase_837() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_837/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_837/../traversal"));
    }
    @Test
    public void testPathSafetyCase_838() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_838/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_838/../traversal"));
    }
    @Test
    public void testPathSafetyCase_839() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_839/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_839/../traversal"));
    }
    @Test
    public void testPathSafetyCase_840() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_840/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_840/../traversal"));
    }
    @Test
    public void testPathSafetyCase_841() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_841/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_841/../traversal"));
    }
    @Test
    public void testPathSafetyCase_842() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_842/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_842/../traversal"));
    }
    @Test
    public void testPathSafetyCase_843() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_843/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_843/../traversal"));
    }
    @Test
    public void testPathSafetyCase_844() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_844/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_844/../traversal"));
    }
    @Test
    public void testPathSafetyCase_845() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_845/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_845/../traversal"));
    }
    @Test
    public void testPathSafetyCase_846() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_846/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_846/../traversal"));
    }
    @Test
    public void testPathSafetyCase_847() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_847/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_847/../traversal"));
    }
    @Test
    public void testPathSafetyCase_848() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_848/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_848/../traversal"));
    }
    @Test
    public void testPathSafetyCase_849() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_849/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_849/../traversal"));
    }
    @Test
    public void testPathSafetyCase_850() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_850/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_850/../traversal"));
    }
    @Test
    public void testPathSafetyCase_851() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_851/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_851/../traversal"));
    }
    @Test
    public void testPathSafetyCase_852() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_852/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_852/../traversal"));
    }
    @Test
    public void testPathSafetyCase_853() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_853/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_853/../traversal"));
    }
    @Test
    public void testPathSafetyCase_854() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_854/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_854/../traversal"));
    }
    @Test
    public void testPathSafetyCase_855() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_855/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_855/../traversal"));
    }
    @Test
    public void testPathSafetyCase_856() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_856/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_856/../traversal"));
    }
    @Test
    public void testPathSafetyCase_857() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_857/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_857/../traversal"));
    }
    @Test
    public void testPathSafetyCase_858() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_858/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_858/../traversal"));
    }
    @Test
    public void testPathSafetyCase_859() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_859/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_859/../traversal"));
    }
    @Test
    public void testPathSafetyCase_860() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_860/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_860/../traversal"));
    }
    @Test
    public void testPathSafetyCase_861() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_861/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_861/../traversal"));
    }
    @Test
    public void testPathSafetyCase_862() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_862/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_862/../traversal"));
    }
    @Test
    public void testPathSafetyCase_863() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_863/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_863/../traversal"));
    }
    @Test
    public void testPathSafetyCase_864() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_864/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_864/../traversal"));
    }
    @Test
    public void testPathSafetyCase_865() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_865/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_865/../traversal"));
    }
    @Test
    public void testPathSafetyCase_866() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_866/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_866/../traversal"));
    }
    @Test
    public void testPathSafetyCase_867() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_867/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_867/../traversal"));
    }
    @Test
    public void testPathSafetyCase_868() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_868/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_868/../traversal"));
    }
    @Test
    public void testPathSafetyCase_869() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_869/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_869/../traversal"));
    }
    @Test
    public void testPathSafetyCase_870() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_870/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_870/../traversal"));
    }
    @Test
    public void testPathSafetyCase_871() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_871/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_871/../traversal"));
    }
    @Test
    public void testPathSafetyCase_872() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_872/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_872/../traversal"));
    }
    @Test
    public void testPathSafetyCase_873() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_873/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_873/../traversal"));
    }
    @Test
    public void testPathSafetyCase_874() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_874/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_874/../traversal"));
    }
    @Test
    public void testPathSafetyCase_875() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_875/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_875/../traversal"));
    }
    @Test
    public void testPathSafetyCase_876() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_876/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_876/../traversal"));
    }
    @Test
    public void testPathSafetyCase_877() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_877/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_877/../traversal"));
    }
    @Test
    public void testPathSafetyCase_878() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_878/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_878/../traversal"));
    }
    @Test
    public void testPathSafetyCase_879() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_879/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_879/../traversal"));
    }
    @Test
    public void testPathSafetyCase_880() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_880/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_880/../traversal"));
    }
    @Test
    public void testPathSafetyCase_881() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_881/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_881/../traversal"));
    }
    @Test
    public void testPathSafetyCase_882() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_882/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_882/../traversal"));
    }
    @Test
    public void testPathSafetyCase_883() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_883/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_883/../traversal"));
    }
    @Test
    public void testPathSafetyCase_884() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_884/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_884/../traversal"));
    }
    @Test
    public void testPathSafetyCase_885() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_885/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_885/../traversal"));
    }
    @Test
    public void testPathSafetyCase_886() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_886/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_886/../traversal"));
    }
    @Test
    public void testPathSafetyCase_887() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_887/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_887/../traversal"));
    }
    @Test
    public void testPathSafetyCase_888() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_888/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_888/../traversal"));
    }
    @Test
    public void testPathSafetyCase_889() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_889/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_889/../traversal"));
    }
    @Test
    public void testPathSafetyCase_890() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_890/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_890/../traversal"));
    }
    @Test
    public void testPathSafetyCase_891() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_891/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_891/../traversal"));
    }
    @Test
    public void testPathSafetyCase_892() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_892/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_892/../traversal"));
    }
    @Test
    public void testPathSafetyCase_893() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_893/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_893/../traversal"));
    }
    @Test
    public void testPathSafetyCase_894() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_894/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_894/../traversal"));
    }
    @Test
    public void testPathSafetyCase_895() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_895/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_895/../traversal"));
    }
    @Test
    public void testPathSafetyCase_896() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_896/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_896/../traversal"));
    }
    @Test
    public void testPathSafetyCase_897() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_897/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_897/../traversal"));
    }
    @Test
    public void testPathSafetyCase_898() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_898/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_898/../traversal"));
    }
    @Test
    public void testPathSafetyCase_899() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_899/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_899/../traversal"));
    }
    @Test
    public void testPathSafetyCase_900() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_900/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_900/../traversal"));
    }
    @Test
    public void testPathSafetyCase_901() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_901/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_901/../traversal"));
    }
    @Test
    public void testPathSafetyCase_902() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_902/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_902/../traversal"));
    }
    @Test
    public void testPathSafetyCase_903() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_903/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_903/../traversal"));
    }
    @Test
    public void testPathSafetyCase_904() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_904/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_904/../traversal"));
    }
    @Test
    public void testPathSafetyCase_905() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_905/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_905/../traversal"));
    }
    @Test
    public void testPathSafetyCase_906() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_906/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_906/../traversal"));
    }
    @Test
    public void testPathSafetyCase_907() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_907/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_907/../traversal"));
    }
    @Test
    public void testPathSafetyCase_908() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_908/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_908/../traversal"));
    }
    @Test
    public void testPathSafetyCase_909() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_909/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_909/../traversal"));
    }
    @Test
    public void testPathSafetyCase_910() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_910/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_910/../traversal"));
    }
    @Test
    public void testPathSafetyCase_911() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_911/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_911/../traversal"));
    }
    @Test
    public void testPathSafetyCase_912() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_912/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_912/../traversal"));
    }
    @Test
    public void testPathSafetyCase_913() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_913/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_913/../traversal"));
    }
    @Test
    public void testPathSafetyCase_914() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_914/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_914/../traversal"));
    }
    @Test
    public void testPathSafetyCase_915() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_915/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_915/../traversal"));
    }
    @Test
    public void testPathSafetyCase_916() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_916/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_916/../traversal"));
    }
    @Test
    public void testPathSafetyCase_917() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_917/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_917/../traversal"));
    }
    @Test
    public void testPathSafetyCase_918() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_918/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_918/../traversal"));
    }
    @Test
    public void testPathSafetyCase_919() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_919/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_919/../traversal"));
    }
    @Test
    public void testPathSafetyCase_920() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_920/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_920/../traversal"));
    }
    @Test
    public void testPathSafetyCase_921() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_921/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_921/../traversal"));
    }
    @Test
    public void testPathSafetyCase_922() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_922/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_922/../traversal"));
    }
    @Test
    public void testPathSafetyCase_923() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_923/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_923/../traversal"));
    }
    @Test
    public void testPathSafetyCase_924() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_924/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_924/../traversal"));
    }
    @Test
    public void testPathSafetyCase_925() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_925/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_925/../traversal"));
    }
    @Test
    public void testPathSafetyCase_926() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_926/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_926/../traversal"));
    }
    @Test
    public void testPathSafetyCase_927() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_927/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_927/../traversal"));
    }
    @Test
    public void testPathSafetyCase_928() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_928/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_928/../traversal"));
    }
    @Test
    public void testPathSafetyCase_929() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_929/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_929/../traversal"));
    }
    @Test
    public void testPathSafetyCase_930() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_930/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_930/../traversal"));
    }
    @Test
    public void testPathSafetyCase_931() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_931/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_931/../traversal"));
    }
    @Test
    public void testPathSafetyCase_932() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_932/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_932/../traversal"));
    }
    @Test
    public void testPathSafetyCase_933() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_933/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_933/../traversal"));
    }
    @Test
    public void testPathSafetyCase_934() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_934/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_934/../traversal"));
    }
    @Test
    public void testPathSafetyCase_935() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_935/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_935/../traversal"));
    }
    @Test
    public void testPathSafetyCase_936() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_936/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_936/../traversal"));
    }
    @Test
    public void testPathSafetyCase_937() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_937/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_937/../traversal"));
    }
    @Test
    public void testPathSafetyCase_938() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_938/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_938/../traversal"));
    }
    @Test
    public void testPathSafetyCase_939() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_939/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_939/../traversal"));
    }
    @Test
    public void testPathSafetyCase_940() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_940/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_940/../traversal"));
    }
    @Test
    public void testPathSafetyCase_941() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_941/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_941/../traversal"));
    }
    @Test
    public void testPathSafetyCase_942() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_942/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_942/../traversal"));
    }
    @Test
    public void testPathSafetyCase_943() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_943/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_943/../traversal"));
    }
    @Test
    public void testPathSafetyCase_944() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_944/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_944/../traversal"));
    }
    @Test
    public void testPathSafetyCase_945() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_945/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_945/../traversal"));
    }
    @Test
    public void testPathSafetyCase_946() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_946/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_946/../traversal"));
    }
    @Test
    public void testPathSafetyCase_947() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_947/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_947/../traversal"));
    }
    @Test
    public void testPathSafetyCase_948() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_948/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_948/../traversal"));
    }
    @Test
    public void testPathSafetyCase_949() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_949/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_949/../traversal"));
    }
    @Test
    public void testPathSafetyCase_950() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_950/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_950/../traversal"));
    }
    @Test
    public void testPathSafetyCase_951() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_951/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_951/../traversal"));
    }
    @Test
    public void testPathSafetyCase_952() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_952/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_952/../traversal"));
    }
    @Test
    public void testPathSafetyCase_953() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_953/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_953/../traversal"));
    }
    @Test
    public void testPathSafetyCase_954() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_954/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_954/../traversal"));
    }
    @Test
    public void testPathSafetyCase_955() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_955/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_955/../traversal"));
    }
    @Test
    public void testPathSafetyCase_956() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_956/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_956/../traversal"));
    }
    @Test
    public void testPathSafetyCase_957() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_957/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_957/../traversal"));
    }
    @Test
    public void testPathSafetyCase_958() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_958/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_958/../traversal"));
    }
    @Test
    public void testPathSafetyCase_959() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_959/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_959/../traversal"));
    }
    @Test
    public void testPathSafetyCase_960() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_960/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_960/../traversal"));
    }
    @Test
    public void testPathSafetyCase_961() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_961/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_961/../traversal"));
    }
    @Test
    public void testPathSafetyCase_962() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_962/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_962/../traversal"));
    }
    @Test
    public void testPathSafetyCase_963() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_963/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_963/../traversal"));
    }
    @Test
    public void testPathSafetyCase_964() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_964/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_964/../traversal"));
    }
    @Test
    public void testPathSafetyCase_965() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_965/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_965/../traversal"));
    }
    @Test
    public void testPathSafetyCase_966() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_966/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_966/../traversal"));
    }
    @Test
    public void testPathSafetyCase_967() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_967/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_967/../traversal"));
    }
    @Test
    public void testPathSafetyCase_968() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_968/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_968/../traversal"));
    }
    @Test
    public void testPathSafetyCase_969() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_969/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_969/../traversal"));
    }
    @Test
    public void testPathSafetyCase_970() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_970/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_970/../traversal"));
    }
    @Test
    public void testPathSafetyCase_971() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_971/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_971/../traversal"));
    }
    @Test
    public void testPathSafetyCase_972() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_972/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_972/../traversal"));
    }
    @Test
    public void testPathSafetyCase_973() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_973/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_973/../traversal"));
    }
    @Test
    public void testPathSafetyCase_974() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_974/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_974/../traversal"));
    }
    @Test
    public void testPathSafetyCase_975() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_975/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_975/../traversal"));
    }
    @Test
    public void testPathSafetyCase_976() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_976/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_976/../traversal"));
    }
    @Test
    public void testPathSafetyCase_977() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_977/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_977/../traversal"));
    }
    @Test
    public void testPathSafetyCase_978() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_978/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_978/../traversal"));
    }
    @Test
    public void testPathSafetyCase_979() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_979/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_979/../traversal"));
    }
    @Test
    public void testPathSafetyCase_980() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_980/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_980/../traversal"));
    }
    @Test
    public void testPathSafetyCase_981() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_981/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_981/../traversal"));
    }
    @Test
    public void testPathSafetyCase_982() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_982/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_982/../traversal"));
    }
    @Test
    public void testPathSafetyCase_983() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_983/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_983/../traversal"));
    }
    @Test
    public void testPathSafetyCase_984() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_984/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_984/../traversal"));
    }
    @Test
    public void testPathSafetyCase_985() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_985/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_985/../traversal"));
    }
    @Test
    public void testPathSafetyCase_986() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_986/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_986/../traversal"));
    }
    @Test
    public void testPathSafetyCase_987() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_987/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_987/../traversal"));
    }
    @Test
    public void testPathSafetyCase_988() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_988/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_988/../traversal"));
    }
    @Test
    public void testPathSafetyCase_989() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_989/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_989/../traversal"));
    }
    @Test
    public void testPathSafetyCase_990() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_990/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_990/../traversal"));
    }
    @Test
    public void testPathSafetyCase_991() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_991/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_991/../traversal"));
    }
    @Test
    public void testPathSafetyCase_992() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_992/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_992/../traversal"));
    }
    @Test
    public void testPathSafetyCase_993() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_993/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_993/../traversal"));
    }
    @Test
    public void testPathSafetyCase_994() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_994/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_994/../traversal"));
    }
    @Test
    public void testPathSafetyCase_995() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_995/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_995/../traversal"));
    }
    @Test
    public void testPathSafetyCase_996() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_996/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_996/../traversal"));
    }
    @Test
    public void testPathSafetyCase_997() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_997/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_997/../traversal"));
    }
    @Test
    public void testPathSafetyCase_998() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_998/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_998/../traversal"));
    }
    @Test
    public void testPathSafetyCase_999() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_999/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_999/../traversal"));
    }
    @Test
    public void testPathSafetyCase_1000() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("safe_path_1000/subdir"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("unsafe_path_1000/../traversal"));
    }
}

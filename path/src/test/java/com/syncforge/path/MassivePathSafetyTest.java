package com.syncforge.path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MassivePathSafetyTest {
    @Test
    public void testPathSafetyCase_1() {
        assertTrue(PathSafetyValidator.isValid("safe_path_1/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_1/../traversal"));
    }
    @Test
    public void testPathSafetyCase_2() {
        assertTrue(PathSafetyValidator.isValid("safe_path_2/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_2/../traversal"));
    }
    @Test
    public void testPathSafetyCase_3() {
        assertTrue(PathSafetyValidator.isValid("safe_path_3/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_3/../traversal"));
    }
    @Test
    public void testPathSafetyCase_4() {
        assertTrue(PathSafetyValidator.isValid("safe_path_4/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_4/../traversal"));
    }
    @Test
    public void testPathSafetyCase_5() {
        assertTrue(PathSafetyValidator.isValid("safe_path_5/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_5/../traversal"));
    }
    @Test
    public void testPathSafetyCase_6() {
        assertTrue(PathSafetyValidator.isValid("safe_path_6/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_6/../traversal"));
    }
    @Test
    public void testPathSafetyCase_7() {
        assertTrue(PathSafetyValidator.isValid("safe_path_7/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_7/../traversal"));
    }
    @Test
    public void testPathSafetyCase_8() {
        assertTrue(PathSafetyValidator.isValid("safe_path_8/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_8/../traversal"));
    }
    @Test
    public void testPathSafetyCase_9() {
        assertTrue(PathSafetyValidator.isValid("safe_path_9/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_9/../traversal"));
    }
    @Test
    public void testPathSafetyCase_10() {
        assertTrue(PathSafetyValidator.isValid("safe_path_10/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_10/../traversal"));
    }
    @Test
    public void testPathSafetyCase_11() {
        assertTrue(PathSafetyValidator.isValid("safe_path_11/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_11/../traversal"));
    }
    @Test
    public void testPathSafetyCase_12() {
        assertTrue(PathSafetyValidator.isValid("safe_path_12/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_12/../traversal"));
    }
    @Test
    public void testPathSafetyCase_13() {
        assertTrue(PathSafetyValidator.isValid("safe_path_13/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_13/../traversal"));
    }
    @Test
    public void testPathSafetyCase_14() {
        assertTrue(PathSafetyValidator.isValid("safe_path_14/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_14/../traversal"));
    }
    @Test
    public void testPathSafetyCase_15() {
        assertTrue(PathSafetyValidator.isValid("safe_path_15/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_15/../traversal"));
    }
    @Test
    public void testPathSafetyCase_16() {
        assertTrue(PathSafetyValidator.isValid("safe_path_16/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_16/../traversal"));
    }
    @Test
    public void testPathSafetyCase_17() {
        assertTrue(PathSafetyValidator.isValid("safe_path_17/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_17/../traversal"));
    }
    @Test
    public void testPathSafetyCase_18() {
        assertTrue(PathSafetyValidator.isValid("safe_path_18/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_18/../traversal"));
    }
    @Test
    public void testPathSafetyCase_19() {
        assertTrue(PathSafetyValidator.isValid("safe_path_19/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_19/../traversal"));
    }
    @Test
    public void testPathSafetyCase_20() {
        assertTrue(PathSafetyValidator.isValid("safe_path_20/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_20/../traversal"));
    }
    @Test
    public void testPathSafetyCase_21() {
        assertTrue(PathSafetyValidator.isValid("safe_path_21/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_21/../traversal"));
    }
    @Test
    public void testPathSafetyCase_22() {
        assertTrue(PathSafetyValidator.isValid("safe_path_22/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_22/../traversal"));
    }
    @Test
    public void testPathSafetyCase_23() {
        assertTrue(PathSafetyValidator.isValid("safe_path_23/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_23/../traversal"));
    }
    @Test
    public void testPathSafetyCase_24() {
        assertTrue(PathSafetyValidator.isValid("safe_path_24/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_24/../traversal"));
    }
    @Test
    public void testPathSafetyCase_25() {
        assertTrue(PathSafetyValidator.isValid("safe_path_25/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_25/../traversal"));
    }
    @Test
    public void testPathSafetyCase_26() {
        assertTrue(PathSafetyValidator.isValid("safe_path_26/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_26/../traversal"));
    }
    @Test
    public void testPathSafetyCase_27() {
        assertTrue(PathSafetyValidator.isValid("safe_path_27/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_27/../traversal"));
    }
    @Test
    public void testPathSafetyCase_28() {
        assertTrue(PathSafetyValidator.isValid("safe_path_28/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_28/../traversal"));
    }
    @Test
    public void testPathSafetyCase_29() {
        assertTrue(PathSafetyValidator.isValid("safe_path_29/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_29/../traversal"));
    }
    @Test
    public void testPathSafetyCase_30() {
        assertTrue(PathSafetyValidator.isValid("safe_path_30/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_30/../traversal"));
    }
    @Test
    public void testPathSafetyCase_31() {
        assertTrue(PathSafetyValidator.isValid("safe_path_31/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_31/../traversal"));
    }
    @Test
    public void testPathSafetyCase_32() {
        assertTrue(PathSafetyValidator.isValid("safe_path_32/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_32/../traversal"));
    }
    @Test
    public void testPathSafetyCase_33() {
        assertTrue(PathSafetyValidator.isValid("safe_path_33/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_33/../traversal"));
    }
    @Test
    public void testPathSafetyCase_34() {
        assertTrue(PathSafetyValidator.isValid("safe_path_34/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_34/../traversal"));
    }
    @Test
    public void testPathSafetyCase_35() {
        assertTrue(PathSafetyValidator.isValid("safe_path_35/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_35/../traversal"));
    }
    @Test
    public void testPathSafetyCase_36() {
        assertTrue(PathSafetyValidator.isValid("safe_path_36/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_36/../traversal"));
    }
    @Test
    public void testPathSafetyCase_37() {
        assertTrue(PathSafetyValidator.isValid("safe_path_37/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_37/../traversal"));
    }
    @Test
    public void testPathSafetyCase_38() {
        assertTrue(PathSafetyValidator.isValid("safe_path_38/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_38/../traversal"));
    }
    @Test
    public void testPathSafetyCase_39() {
        assertTrue(PathSafetyValidator.isValid("safe_path_39/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_39/../traversal"));
    }
    @Test
    public void testPathSafetyCase_40() {
        assertTrue(PathSafetyValidator.isValid("safe_path_40/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_40/../traversal"));
    }
    @Test
    public void testPathSafetyCase_41() {
        assertTrue(PathSafetyValidator.isValid("safe_path_41/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_41/../traversal"));
    }
    @Test
    public void testPathSafetyCase_42() {
        assertTrue(PathSafetyValidator.isValid("safe_path_42/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_42/../traversal"));
    }
    @Test
    public void testPathSafetyCase_43() {
        assertTrue(PathSafetyValidator.isValid("safe_path_43/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_43/../traversal"));
    }
    @Test
    public void testPathSafetyCase_44() {
        assertTrue(PathSafetyValidator.isValid("safe_path_44/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_44/../traversal"));
    }
    @Test
    public void testPathSafetyCase_45() {
        assertTrue(PathSafetyValidator.isValid("safe_path_45/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_45/../traversal"));
    }
    @Test
    public void testPathSafetyCase_46() {
        assertTrue(PathSafetyValidator.isValid("safe_path_46/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_46/../traversal"));
    }
    @Test
    public void testPathSafetyCase_47() {
        assertTrue(PathSafetyValidator.isValid("safe_path_47/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_47/../traversal"));
    }
    @Test
    public void testPathSafetyCase_48() {
        assertTrue(PathSafetyValidator.isValid("safe_path_48/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_48/../traversal"));
    }
    @Test
    public void testPathSafetyCase_49() {
        assertTrue(PathSafetyValidator.isValid("safe_path_49/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_49/../traversal"));
    }
    @Test
    public void testPathSafetyCase_50() {
        assertTrue(PathSafetyValidator.isValid("safe_path_50/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_50/../traversal"));
    }
    @Test
    public void testPathSafetyCase_51() {
        assertTrue(PathSafetyValidator.isValid("safe_path_51/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_51/../traversal"));
    }
    @Test
    public void testPathSafetyCase_52() {
        assertTrue(PathSafetyValidator.isValid("safe_path_52/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_52/../traversal"));
    }
    @Test
    public void testPathSafetyCase_53() {
        assertTrue(PathSafetyValidator.isValid("safe_path_53/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_53/../traversal"));
    }
    @Test
    public void testPathSafetyCase_54() {
        assertTrue(PathSafetyValidator.isValid("safe_path_54/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_54/../traversal"));
    }
    @Test
    public void testPathSafetyCase_55() {
        assertTrue(PathSafetyValidator.isValid("safe_path_55/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_55/../traversal"));
    }
    @Test
    public void testPathSafetyCase_56() {
        assertTrue(PathSafetyValidator.isValid("safe_path_56/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_56/../traversal"));
    }
    @Test
    public void testPathSafetyCase_57() {
        assertTrue(PathSafetyValidator.isValid("safe_path_57/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_57/../traversal"));
    }
    @Test
    public void testPathSafetyCase_58() {
        assertTrue(PathSafetyValidator.isValid("safe_path_58/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_58/../traversal"));
    }
    @Test
    public void testPathSafetyCase_59() {
        assertTrue(PathSafetyValidator.isValid("safe_path_59/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_59/../traversal"));
    }
    @Test
    public void testPathSafetyCase_60() {
        assertTrue(PathSafetyValidator.isValid("safe_path_60/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_60/../traversal"));
    }
    @Test
    public void testPathSafetyCase_61() {
        assertTrue(PathSafetyValidator.isValid("safe_path_61/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_61/../traversal"));
    }
    @Test
    public void testPathSafetyCase_62() {
        assertTrue(PathSafetyValidator.isValid("safe_path_62/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_62/../traversal"));
    }
    @Test
    public void testPathSafetyCase_63() {
        assertTrue(PathSafetyValidator.isValid("safe_path_63/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_63/../traversal"));
    }
    @Test
    public void testPathSafetyCase_64() {
        assertTrue(PathSafetyValidator.isValid("safe_path_64/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_64/../traversal"));
    }
    @Test
    public void testPathSafetyCase_65() {
        assertTrue(PathSafetyValidator.isValid("safe_path_65/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_65/../traversal"));
    }
    @Test
    public void testPathSafetyCase_66() {
        assertTrue(PathSafetyValidator.isValid("safe_path_66/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_66/../traversal"));
    }
    @Test
    public void testPathSafetyCase_67() {
        assertTrue(PathSafetyValidator.isValid("safe_path_67/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_67/../traversal"));
    }
    @Test
    public void testPathSafetyCase_68() {
        assertTrue(PathSafetyValidator.isValid("safe_path_68/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_68/../traversal"));
    }
    @Test
    public void testPathSafetyCase_69() {
        assertTrue(PathSafetyValidator.isValid("safe_path_69/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_69/../traversal"));
    }
    @Test
    public void testPathSafetyCase_70() {
        assertTrue(PathSafetyValidator.isValid("safe_path_70/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_70/../traversal"));
    }
    @Test
    public void testPathSafetyCase_71() {
        assertTrue(PathSafetyValidator.isValid("safe_path_71/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_71/../traversal"));
    }
    @Test
    public void testPathSafetyCase_72() {
        assertTrue(PathSafetyValidator.isValid("safe_path_72/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_72/../traversal"));
    }
    @Test
    public void testPathSafetyCase_73() {
        assertTrue(PathSafetyValidator.isValid("safe_path_73/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_73/../traversal"));
    }
    @Test
    public void testPathSafetyCase_74() {
        assertTrue(PathSafetyValidator.isValid("safe_path_74/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_74/../traversal"));
    }
    @Test
    public void testPathSafetyCase_75() {
        assertTrue(PathSafetyValidator.isValid("safe_path_75/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_75/../traversal"));
    }
    @Test
    public void testPathSafetyCase_76() {
        assertTrue(PathSafetyValidator.isValid("safe_path_76/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_76/../traversal"));
    }
    @Test
    public void testPathSafetyCase_77() {
        assertTrue(PathSafetyValidator.isValid("safe_path_77/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_77/../traversal"));
    }
    @Test
    public void testPathSafetyCase_78() {
        assertTrue(PathSafetyValidator.isValid("safe_path_78/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_78/../traversal"));
    }
    @Test
    public void testPathSafetyCase_79() {
        assertTrue(PathSafetyValidator.isValid("safe_path_79/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_79/../traversal"));
    }
    @Test
    public void testPathSafetyCase_80() {
        assertTrue(PathSafetyValidator.isValid("safe_path_80/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_80/../traversal"));
    }
    @Test
    public void testPathSafetyCase_81() {
        assertTrue(PathSafetyValidator.isValid("safe_path_81/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_81/../traversal"));
    }
    @Test
    public void testPathSafetyCase_82() {
        assertTrue(PathSafetyValidator.isValid("safe_path_82/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_82/../traversal"));
    }
    @Test
    public void testPathSafetyCase_83() {
        assertTrue(PathSafetyValidator.isValid("safe_path_83/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_83/../traversal"));
    }
    @Test
    public void testPathSafetyCase_84() {
        assertTrue(PathSafetyValidator.isValid("safe_path_84/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_84/../traversal"));
    }
    @Test
    public void testPathSafetyCase_85() {
        assertTrue(PathSafetyValidator.isValid("safe_path_85/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_85/../traversal"));
    }
    @Test
    public void testPathSafetyCase_86() {
        assertTrue(PathSafetyValidator.isValid("safe_path_86/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_86/../traversal"));
    }
    @Test
    public void testPathSafetyCase_87() {
        assertTrue(PathSafetyValidator.isValid("safe_path_87/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_87/../traversal"));
    }
    @Test
    public void testPathSafetyCase_88() {
        assertTrue(PathSafetyValidator.isValid("safe_path_88/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_88/../traversal"));
    }
    @Test
    public void testPathSafetyCase_89() {
        assertTrue(PathSafetyValidator.isValid("safe_path_89/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_89/../traversal"));
    }
    @Test
    public void testPathSafetyCase_90() {
        assertTrue(PathSafetyValidator.isValid("safe_path_90/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_90/../traversal"));
    }
    @Test
    public void testPathSafetyCase_91() {
        assertTrue(PathSafetyValidator.isValid("safe_path_91/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_91/../traversal"));
    }
    @Test
    public void testPathSafetyCase_92() {
        assertTrue(PathSafetyValidator.isValid("safe_path_92/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_92/../traversal"));
    }
    @Test
    public void testPathSafetyCase_93() {
        assertTrue(PathSafetyValidator.isValid("safe_path_93/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_93/../traversal"));
    }
    @Test
    public void testPathSafetyCase_94() {
        assertTrue(PathSafetyValidator.isValid("safe_path_94/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_94/../traversal"));
    }
    @Test
    public void testPathSafetyCase_95() {
        assertTrue(PathSafetyValidator.isValid("safe_path_95/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_95/../traversal"));
    }
    @Test
    public void testPathSafetyCase_96() {
        assertTrue(PathSafetyValidator.isValid("safe_path_96/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_96/../traversal"));
    }
    @Test
    public void testPathSafetyCase_97() {
        assertTrue(PathSafetyValidator.isValid("safe_path_97/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_97/../traversal"));
    }
    @Test
    public void testPathSafetyCase_98() {
        assertTrue(PathSafetyValidator.isValid("safe_path_98/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_98/../traversal"));
    }
    @Test
    public void testPathSafetyCase_99() {
        assertTrue(PathSafetyValidator.isValid("safe_path_99/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_99/../traversal"));
    }
    @Test
    public void testPathSafetyCase_100() {
        assertTrue(PathSafetyValidator.isValid("safe_path_100/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_100/../traversal"));
    }
    @Test
    public void testPathSafetyCase_101() {
        assertTrue(PathSafetyValidator.isValid("safe_path_101/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_101/../traversal"));
    }
    @Test
    public void testPathSafetyCase_102() {
        assertTrue(PathSafetyValidator.isValid("safe_path_102/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_102/../traversal"));
    }
    @Test
    public void testPathSafetyCase_103() {
        assertTrue(PathSafetyValidator.isValid("safe_path_103/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_103/../traversal"));
    }
    @Test
    public void testPathSafetyCase_104() {
        assertTrue(PathSafetyValidator.isValid("safe_path_104/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_104/../traversal"));
    }
    @Test
    public void testPathSafetyCase_105() {
        assertTrue(PathSafetyValidator.isValid("safe_path_105/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_105/../traversal"));
    }
    @Test
    public void testPathSafetyCase_106() {
        assertTrue(PathSafetyValidator.isValid("safe_path_106/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_106/../traversal"));
    }
    @Test
    public void testPathSafetyCase_107() {
        assertTrue(PathSafetyValidator.isValid("safe_path_107/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_107/../traversal"));
    }
    @Test
    public void testPathSafetyCase_108() {
        assertTrue(PathSafetyValidator.isValid("safe_path_108/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_108/../traversal"));
    }
    @Test
    public void testPathSafetyCase_109() {
        assertTrue(PathSafetyValidator.isValid("safe_path_109/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_109/../traversal"));
    }
    @Test
    public void testPathSafetyCase_110() {
        assertTrue(PathSafetyValidator.isValid("safe_path_110/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_110/../traversal"));
    }
    @Test
    public void testPathSafetyCase_111() {
        assertTrue(PathSafetyValidator.isValid("safe_path_111/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_111/../traversal"));
    }
    @Test
    public void testPathSafetyCase_112() {
        assertTrue(PathSafetyValidator.isValid("safe_path_112/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_112/../traversal"));
    }
    @Test
    public void testPathSafetyCase_113() {
        assertTrue(PathSafetyValidator.isValid("safe_path_113/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_113/../traversal"));
    }
    @Test
    public void testPathSafetyCase_114() {
        assertTrue(PathSafetyValidator.isValid("safe_path_114/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_114/../traversal"));
    }
    @Test
    public void testPathSafetyCase_115() {
        assertTrue(PathSafetyValidator.isValid("safe_path_115/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_115/../traversal"));
    }
    @Test
    public void testPathSafetyCase_116() {
        assertTrue(PathSafetyValidator.isValid("safe_path_116/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_116/../traversal"));
    }
    @Test
    public void testPathSafetyCase_117() {
        assertTrue(PathSafetyValidator.isValid("safe_path_117/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_117/../traversal"));
    }
    @Test
    public void testPathSafetyCase_118() {
        assertTrue(PathSafetyValidator.isValid("safe_path_118/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_118/../traversal"));
    }
    @Test
    public void testPathSafetyCase_119() {
        assertTrue(PathSafetyValidator.isValid("safe_path_119/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_119/../traversal"));
    }
    @Test
    public void testPathSafetyCase_120() {
        assertTrue(PathSafetyValidator.isValid("safe_path_120/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_120/../traversal"));
    }
    @Test
    public void testPathSafetyCase_121() {
        assertTrue(PathSafetyValidator.isValid("safe_path_121/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_121/../traversal"));
    }
    @Test
    public void testPathSafetyCase_122() {
        assertTrue(PathSafetyValidator.isValid("safe_path_122/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_122/../traversal"));
    }
    @Test
    public void testPathSafetyCase_123() {
        assertTrue(PathSafetyValidator.isValid("safe_path_123/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_123/../traversal"));
    }
    @Test
    public void testPathSafetyCase_124() {
        assertTrue(PathSafetyValidator.isValid("safe_path_124/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_124/../traversal"));
    }
    @Test
    public void testPathSafetyCase_125() {
        assertTrue(PathSafetyValidator.isValid("safe_path_125/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_125/../traversal"));
    }
    @Test
    public void testPathSafetyCase_126() {
        assertTrue(PathSafetyValidator.isValid("safe_path_126/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_126/../traversal"));
    }
    @Test
    public void testPathSafetyCase_127() {
        assertTrue(PathSafetyValidator.isValid("safe_path_127/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_127/../traversal"));
    }
    @Test
    public void testPathSafetyCase_128() {
        assertTrue(PathSafetyValidator.isValid("safe_path_128/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_128/../traversal"));
    }
    @Test
    public void testPathSafetyCase_129() {
        assertTrue(PathSafetyValidator.isValid("safe_path_129/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_129/../traversal"));
    }
    @Test
    public void testPathSafetyCase_130() {
        assertTrue(PathSafetyValidator.isValid("safe_path_130/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_130/../traversal"));
    }
    @Test
    public void testPathSafetyCase_131() {
        assertTrue(PathSafetyValidator.isValid("safe_path_131/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_131/../traversal"));
    }
    @Test
    public void testPathSafetyCase_132() {
        assertTrue(PathSafetyValidator.isValid("safe_path_132/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_132/../traversal"));
    }
    @Test
    public void testPathSafetyCase_133() {
        assertTrue(PathSafetyValidator.isValid("safe_path_133/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_133/../traversal"));
    }
    @Test
    public void testPathSafetyCase_134() {
        assertTrue(PathSafetyValidator.isValid("safe_path_134/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_134/../traversal"));
    }
    @Test
    public void testPathSafetyCase_135() {
        assertTrue(PathSafetyValidator.isValid("safe_path_135/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_135/../traversal"));
    }
    @Test
    public void testPathSafetyCase_136() {
        assertTrue(PathSafetyValidator.isValid("safe_path_136/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_136/../traversal"));
    }
    @Test
    public void testPathSafetyCase_137() {
        assertTrue(PathSafetyValidator.isValid("safe_path_137/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_137/../traversal"));
    }
    @Test
    public void testPathSafetyCase_138() {
        assertTrue(PathSafetyValidator.isValid("safe_path_138/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_138/../traversal"));
    }
    @Test
    public void testPathSafetyCase_139() {
        assertTrue(PathSafetyValidator.isValid("safe_path_139/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_139/../traversal"));
    }
    @Test
    public void testPathSafetyCase_140() {
        assertTrue(PathSafetyValidator.isValid("safe_path_140/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_140/../traversal"));
    }
    @Test
    public void testPathSafetyCase_141() {
        assertTrue(PathSafetyValidator.isValid("safe_path_141/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_141/../traversal"));
    }
    @Test
    public void testPathSafetyCase_142() {
        assertTrue(PathSafetyValidator.isValid("safe_path_142/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_142/../traversal"));
    }
    @Test
    public void testPathSafetyCase_143() {
        assertTrue(PathSafetyValidator.isValid("safe_path_143/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_143/../traversal"));
    }
    @Test
    public void testPathSafetyCase_144() {
        assertTrue(PathSafetyValidator.isValid("safe_path_144/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_144/../traversal"));
    }
    @Test
    public void testPathSafetyCase_145() {
        assertTrue(PathSafetyValidator.isValid("safe_path_145/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_145/../traversal"));
    }
    @Test
    public void testPathSafetyCase_146() {
        assertTrue(PathSafetyValidator.isValid("safe_path_146/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_146/../traversal"));
    }
    @Test
    public void testPathSafetyCase_147() {
        assertTrue(PathSafetyValidator.isValid("safe_path_147/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_147/../traversal"));
    }
    @Test
    public void testPathSafetyCase_148() {
        assertTrue(PathSafetyValidator.isValid("safe_path_148/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_148/../traversal"));
    }
    @Test
    public void testPathSafetyCase_149() {
        assertTrue(PathSafetyValidator.isValid("safe_path_149/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_149/../traversal"));
    }
    @Test
    public void testPathSafetyCase_150() {
        assertTrue(PathSafetyValidator.isValid("safe_path_150/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_150/../traversal"));
    }
    @Test
    public void testPathSafetyCase_151() {
        assertTrue(PathSafetyValidator.isValid("safe_path_151/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_151/../traversal"));
    }
    @Test
    public void testPathSafetyCase_152() {
        assertTrue(PathSafetyValidator.isValid("safe_path_152/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_152/../traversal"));
    }
    @Test
    public void testPathSafetyCase_153() {
        assertTrue(PathSafetyValidator.isValid("safe_path_153/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_153/../traversal"));
    }
    @Test
    public void testPathSafetyCase_154() {
        assertTrue(PathSafetyValidator.isValid("safe_path_154/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_154/../traversal"));
    }
    @Test
    public void testPathSafetyCase_155() {
        assertTrue(PathSafetyValidator.isValid("safe_path_155/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_155/../traversal"));
    }
    @Test
    public void testPathSafetyCase_156() {
        assertTrue(PathSafetyValidator.isValid("safe_path_156/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_156/../traversal"));
    }
    @Test
    public void testPathSafetyCase_157() {
        assertTrue(PathSafetyValidator.isValid("safe_path_157/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_157/../traversal"));
    }
    @Test
    public void testPathSafetyCase_158() {
        assertTrue(PathSafetyValidator.isValid("safe_path_158/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_158/../traversal"));
    }
    @Test
    public void testPathSafetyCase_159() {
        assertTrue(PathSafetyValidator.isValid("safe_path_159/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_159/../traversal"));
    }
    @Test
    public void testPathSafetyCase_160() {
        assertTrue(PathSafetyValidator.isValid("safe_path_160/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_160/../traversal"));
    }
    @Test
    public void testPathSafetyCase_161() {
        assertTrue(PathSafetyValidator.isValid("safe_path_161/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_161/../traversal"));
    }
    @Test
    public void testPathSafetyCase_162() {
        assertTrue(PathSafetyValidator.isValid("safe_path_162/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_162/../traversal"));
    }
    @Test
    public void testPathSafetyCase_163() {
        assertTrue(PathSafetyValidator.isValid("safe_path_163/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_163/../traversal"));
    }
    @Test
    public void testPathSafetyCase_164() {
        assertTrue(PathSafetyValidator.isValid("safe_path_164/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_164/../traversal"));
    }
    @Test
    public void testPathSafetyCase_165() {
        assertTrue(PathSafetyValidator.isValid("safe_path_165/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_165/../traversal"));
    }
    @Test
    public void testPathSafetyCase_166() {
        assertTrue(PathSafetyValidator.isValid("safe_path_166/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_166/../traversal"));
    }
    @Test
    public void testPathSafetyCase_167() {
        assertTrue(PathSafetyValidator.isValid("safe_path_167/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_167/../traversal"));
    }
    @Test
    public void testPathSafetyCase_168() {
        assertTrue(PathSafetyValidator.isValid("safe_path_168/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_168/../traversal"));
    }
    @Test
    public void testPathSafetyCase_169() {
        assertTrue(PathSafetyValidator.isValid("safe_path_169/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_169/../traversal"));
    }
    @Test
    public void testPathSafetyCase_170() {
        assertTrue(PathSafetyValidator.isValid("safe_path_170/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_170/../traversal"));
    }
    @Test
    public void testPathSafetyCase_171() {
        assertTrue(PathSafetyValidator.isValid("safe_path_171/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_171/../traversal"));
    }
    @Test
    public void testPathSafetyCase_172() {
        assertTrue(PathSafetyValidator.isValid("safe_path_172/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_172/../traversal"));
    }
    @Test
    public void testPathSafetyCase_173() {
        assertTrue(PathSafetyValidator.isValid("safe_path_173/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_173/../traversal"));
    }
    @Test
    public void testPathSafetyCase_174() {
        assertTrue(PathSafetyValidator.isValid("safe_path_174/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_174/../traversal"));
    }
    @Test
    public void testPathSafetyCase_175() {
        assertTrue(PathSafetyValidator.isValid("safe_path_175/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_175/../traversal"));
    }
    @Test
    public void testPathSafetyCase_176() {
        assertTrue(PathSafetyValidator.isValid("safe_path_176/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_176/../traversal"));
    }
    @Test
    public void testPathSafetyCase_177() {
        assertTrue(PathSafetyValidator.isValid("safe_path_177/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_177/../traversal"));
    }
    @Test
    public void testPathSafetyCase_178() {
        assertTrue(PathSafetyValidator.isValid("safe_path_178/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_178/../traversal"));
    }
    @Test
    public void testPathSafetyCase_179() {
        assertTrue(PathSafetyValidator.isValid("safe_path_179/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_179/../traversal"));
    }
    @Test
    public void testPathSafetyCase_180() {
        assertTrue(PathSafetyValidator.isValid("safe_path_180/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_180/../traversal"));
    }
    @Test
    public void testPathSafetyCase_181() {
        assertTrue(PathSafetyValidator.isValid("safe_path_181/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_181/../traversal"));
    }
    @Test
    public void testPathSafetyCase_182() {
        assertTrue(PathSafetyValidator.isValid("safe_path_182/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_182/../traversal"));
    }
    @Test
    public void testPathSafetyCase_183() {
        assertTrue(PathSafetyValidator.isValid("safe_path_183/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_183/../traversal"));
    }
    @Test
    public void testPathSafetyCase_184() {
        assertTrue(PathSafetyValidator.isValid("safe_path_184/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_184/../traversal"));
    }
    @Test
    public void testPathSafetyCase_185() {
        assertTrue(PathSafetyValidator.isValid("safe_path_185/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_185/../traversal"));
    }
    @Test
    public void testPathSafetyCase_186() {
        assertTrue(PathSafetyValidator.isValid("safe_path_186/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_186/../traversal"));
    }
    @Test
    public void testPathSafetyCase_187() {
        assertTrue(PathSafetyValidator.isValid("safe_path_187/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_187/../traversal"));
    }
    @Test
    public void testPathSafetyCase_188() {
        assertTrue(PathSafetyValidator.isValid("safe_path_188/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_188/../traversal"));
    }
    @Test
    public void testPathSafetyCase_189() {
        assertTrue(PathSafetyValidator.isValid("safe_path_189/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_189/../traversal"));
    }
    @Test
    public void testPathSafetyCase_190() {
        assertTrue(PathSafetyValidator.isValid("safe_path_190/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_190/../traversal"));
    }
    @Test
    public void testPathSafetyCase_191() {
        assertTrue(PathSafetyValidator.isValid("safe_path_191/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_191/../traversal"));
    }
    @Test
    public void testPathSafetyCase_192() {
        assertTrue(PathSafetyValidator.isValid("safe_path_192/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_192/../traversal"));
    }
    @Test
    public void testPathSafetyCase_193() {
        assertTrue(PathSafetyValidator.isValid("safe_path_193/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_193/../traversal"));
    }
    @Test
    public void testPathSafetyCase_194() {
        assertTrue(PathSafetyValidator.isValid("safe_path_194/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_194/../traversal"));
    }
    @Test
    public void testPathSafetyCase_195() {
        assertTrue(PathSafetyValidator.isValid("safe_path_195/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_195/../traversal"));
    }
    @Test
    public void testPathSafetyCase_196() {
        assertTrue(PathSafetyValidator.isValid("safe_path_196/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_196/../traversal"));
    }
    @Test
    public void testPathSafetyCase_197() {
        assertTrue(PathSafetyValidator.isValid("safe_path_197/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_197/../traversal"));
    }
    @Test
    public void testPathSafetyCase_198() {
        assertTrue(PathSafetyValidator.isValid("safe_path_198/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_198/../traversal"));
    }
    @Test
    public void testPathSafetyCase_199() {
        assertTrue(PathSafetyValidator.isValid("safe_path_199/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_199/../traversal"));
    }
    @Test
    public void testPathSafetyCase_200() {
        assertTrue(PathSafetyValidator.isValid("safe_path_200/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_200/../traversal"));
    }
    @Test
    public void testPathSafetyCase_201() {
        assertTrue(PathSafetyValidator.isValid("safe_path_201/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_201/../traversal"));
    }
    @Test
    public void testPathSafetyCase_202() {
        assertTrue(PathSafetyValidator.isValid("safe_path_202/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_202/../traversal"));
    }
    @Test
    public void testPathSafetyCase_203() {
        assertTrue(PathSafetyValidator.isValid("safe_path_203/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_203/../traversal"));
    }
    @Test
    public void testPathSafetyCase_204() {
        assertTrue(PathSafetyValidator.isValid("safe_path_204/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_204/../traversal"));
    }
    @Test
    public void testPathSafetyCase_205() {
        assertTrue(PathSafetyValidator.isValid("safe_path_205/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_205/../traversal"));
    }
    @Test
    public void testPathSafetyCase_206() {
        assertTrue(PathSafetyValidator.isValid("safe_path_206/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_206/../traversal"));
    }
    @Test
    public void testPathSafetyCase_207() {
        assertTrue(PathSafetyValidator.isValid("safe_path_207/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_207/../traversal"));
    }
    @Test
    public void testPathSafetyCase_208() {
        assertTrue(PathSafetyValidator.isValid("safe_path_208/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_208/../traversal"));
    }
    @Test
    public void testPathSafetyCase_209() {
        assertTrue(PathSafetyValidator.isValid("safe_path_209/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_209/../traversal"));
    }
    @Test
    public void testPathSafetyCase_210() {
        assertTrue(PathSafetyValidator.isValid("safe_path_210/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_210/../traversal"));
    }
    @Test
    public void testPathSafetyCase_211() {
        assertTrue(PathSafetyValidator.isValid("safe_path_211/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_211/../traversal"));
    }
    @Test
    public void testPathSafetyCase_212() {
        assertTrue(PathSafetyValidator.isValid("safe_path_212/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_212/../traversal"));
    }
    @Test
    public void testPathSafetyCase_213() {
        assertTrue(PathSafetyValidator.isValid("safe_path_213/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_213/../traversal"));
    }
    @Test
    public void testPathSafetyCase_214() {
        assertTrue(PathSafetyValidator.isValid("safe_path_214/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_214/../traversal"));
    }
    @Test
    public void testPathSafetyCase_215() {
        assertTrue(PathSafetyValidator.isValid("safe_path_215/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_215/../traversal"));
    }
    @Test
    public void testPathSafetyCase_216() {
        assertTrue(PathSafetyValidator.isValid("safe_path_216/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_216/../traversal"));
    }
    @Test
    public void testPathSafetyCase_217() {
        assertTrue(PathSafetyValidator.isValid("safe_path_217/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_217/../traversal"));
    }
    @Test
    public void testPathSafetyCase_218() {
        assertTrue(PathSafetyValidator.isValid("safe_path_218/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_218/../traversal"));
    }
    @Test
    public void testPathSafetyCase_219() {
        assertTrue(PathSafetyValidator.isValid("safe_path_219/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_219/../traversal"));
    }
    @Test
    public void testPathSafetyCase_220() {
        assertTrue(PathSafetyValidator.isValid("safe_path_220/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_220/../traversal"));
    }
    @Test
    public void testPathSafetyCase_221() {
        assertTrue(PathSafetyValidator.isValid("safe_path_221/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_221/../traversal"));
    }
    @Test
    public void testPathSafetyCase_222() {
        assertTrue(PathSafetyValidator.isValid("safe_path_222/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_222/../traversal"));
    }
    @Test
    public void testPathSafetyCase_223() {
        assertTrue(PathSafetyValidator.isValid("safe_path_223/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_223/../traversal"));
    }
    @Test
    public void testPathSafetyCase_224() {
        assertTrue(PathSafetyValidator.isValid("safe_path_224/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_224/../traversal"));
    }
    @Test
    public void testPathSafetyCase_225() {
        assertTrue(PathSafetyValidator.isValid("safe_path_225/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_225/../traversal"));
    }
    @Test
    public void testPathSafetyCase_226() {
        assertTrue(PathSafetyValidator.isValid("safe_path_226/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_226/../traversal"));
    }
    @Test
    public void testPathSafetyCase_227() {
        assertTrue(PathSafetyValidator.isValid("safe_path_227/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_227/../traversal"));
    }
    @Test
    public void testPathSafetyCase_228() {
        assertTrue(PathSafetyValidator.isValid("safe_path_228/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_228/../traversal"));
    }
    @Test
    public void testPathSafetyCase_229() {
        assertTrue(PathSafetyValidator.isValid("safe_path_229/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_229/../traversal"));
    }
    @Test
    public void testPathSafetyCase_230() {
        assertTrue(PathSafetyValidator.isValid("safe_path_230/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_230/../traversal"));
    }
    @Test
    public void testPathSafetyCase_231() {
        assertTrue(PathSafetyValidator.isValid("safe_path_231/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_231/../traversal"));
    }
    @Test
    public void testPathSafetyCase_232() {
        assertTrue(PathSafetyValidator.isValid("safe_path_232/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_232/../traversal"));
    }
    @Test
    public void testPathSafetyCase_233() {
        assertTrue(PathSafetyValidator.isValid("safe_path_233/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_233/../traversal"));
    }
    @Test
    public void testPathSafetyCase_234() {
        assertTrue(PathSafetyValidator.isValid("safe_path_234/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_234/../traversal"));
    }
    @Test
    public void testPathSafetyCase_235() {
        assertTrue(PathSafetyValidator.isValid("safe_path_235/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_235/../traversal"));
    }
    @Test
    public void testPathSafetyCase_236() {
        assertTrue(PathSafetyValidator.isValid("safe_path_236/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_236/../traversal"));
    }
    @Test
    public void testPathSafetyCase_237() {
        assertTrue(PathSafetyValidator.isValid("safe_path_237/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_237/../traversal"));
    }
    @Test
    public void testPathSafetyCase_238() {
        assertTrue(PathSafetyValidator.isValid("safe_path_238/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_238/../traversal"));
    }
    @Test
    public void testPathSafetyCase_239() {
        assertTrue(PathSafetyValidator.isValid("safe_path_239/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_239/../traversal"));
    }
    @Test
    public void testPathSafetyCase_240() {
        assertTrue(PathSafetyValidator.isValid("safe_path_240/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_240/../traversal"));
    }
    @Test
    public void testPathSafetyCase_241() {
        assertTrue(PathSafetyValidator.isValid("safe_path_241/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_241/../traversal"));
    }
    @Test
    public void testPathSafetyCase_242() {
        assertTrue(PathSafetyValidator.isValid("safe_path_242/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_242/../traversal"));
    }
    @Test
    public void testPathSafetyCase_243() {
        assertTrue(PathSafetyValidator.isValid("safe_path_243/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_243/../traversal"));
    }
    @Test
    public void testPathSafetyCase_244() {
        assertTrue(PathSafetyValidator.isValid("safe_path_244/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_244/../traversal"));
    }
    @Test
    public void testPathSafetyCase_245() {
        assertTrue(PathSafetyValidator.isValid("safe_path_245/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_245/../traversal"));
    }
    @Test
    public void testPathSafetyCase_246() {
        assertTrue(PathSafetyValidator.isValid("safe_path_246/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_246/../traversal"));
    }
    @Test
    public void testPathSafetyCase_247() {
        assertTrue(PathSafetyValidator.isValid("safe_path_247/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_247/../traversal"));
    }
    @Test
    public void testPathSafetyCase_248() {
        assertTrue(PathSafetyValidator.isValid("safe_path_248/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_248/../traversal"));
    }
    @Test
    public void testPathSafetyCase_249() {
        assertTrue(PathSafetyValidator.isValid("safe_path_249/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_249/../traversal"));
    }
    @Test
    public void testPathSafetyCase_250() {
        assertTrue(PathSafetyValidator.isValid("safe_path_250/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_250/../traversal"));
    }
    @Test
    public void testPathSafetyCase_251() {
        assertTrue(PathSafetyValidator.isValid("safe_path_251/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_251/../traversal"));
    }
    @Test
    public void testPathSafetyCase_252() {
        assertTrue(PathSafetyValidator.isValid("safe_path_252/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_252/../traversal"));
    }
    @Test
    public void testPathSafetyCase_253() {
        assertTrue(PathSafetyValidator.isValid("safe_path_253/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_253/../traversal"));
    }
    @Test
    public void testPathSafetyCase_254() {
        assertTrue(PathSafetyValidator.isValid("safe_path_254/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_254/../traversal"));
    }
    @Test
    public void testPathSafetyCase_255() {
        assertTrue(PathSafetyValidator.isValid("safe_path_255/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_255/../traversal"));
    }
    @Test
    public void testPathSafetyCase_256() {
        assertTrue(PathSafetyValidator.isValid("safe_path_256/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_256/../traversal"));
    }
    @Test
    public void testPathSafetyCase_257() {
        assertTrue(PathSafetyValidator.isValid("safe_path_257/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_257/../traversal"));
    }
    @Test
    public void testPathSafetyCase_258() {
        assertTrue(PathSafetyValidator.isValid("safe_path_258/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_258/../traversal"));
    }
    @Test
    public void testPathSafetyCase_259() {
        assertTrue(PathSafetyValidator.isValid("safe_path_259/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_259/../traversal"));
    }
    @Test
    public void testPathSafetyCase_260() {
        assertTrue(PathSafetyValidator.isValid("safe_path_260/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_260/../traversal"));
    }
    @Test
    public void testPathSafetyCase_261() {
        assertTrue(PathSafetyValidator.isValid("safe_path_261/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_261/../traversal"));
    }
    @Test
    public void testPathSafetyCase_262() {
        assertTrue(PathSafetyValidator.isValid("safe_path_262/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_262/../traversal"));
    }
    @Test
    public void testPathSafetyCase_263() {
        assertTrue(PathSafetyValidator.isValid("safe_path_263/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_263/../traversal"));
    }
    @Test
    public void testPathSafetyCase_264() {
        assertTrue(PathSafetyValidator.isValid("safe_path_264/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_264/../traversal"));
    }
    @Test
    public void testPathSafetyCase_265() {
        assertTrue(PathSafetyValidator.isValid("safe_path_265/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_265/../traversal"));
    }
    @Test
    public void testPathSafetyCase_266() {
        assertTrue(PathSafetyValidator.isValid("safe_path_266/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_266/../traversal"));
    }
    @Test
    public void testPathSafetyCase_267() {
        assertTrue(PathSafetyValidator.isValid("safe_path_267/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_267/../traversal"));
    }
    @Test
    public void testPathSafetyCase_268() {
        assertTrue(PathSafetyValidator.isValid("safe_path_268/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_268/../traversal"));
    }
    @Test
    public void testPathSafetyCase_269() {
        assertTrue(PathSafetyValidator.isValid("safe_path_269/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_269/../traversal"));
    }
    @Test
    public void testPathSafetyCase_270() {
        assertTrue(PathSafetyValidator.isValid("safe_path_270/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_270/../traversal"));
    }
    @Test
    public void testPathSafetyCase_271() {
        assertTrue(PathSafetyValidator.isValid("safe_path_271/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_271/../traversal"));
    }
    @Test
    public void testPathSafetyCase_272() {
        assertTrue(PathSafetyValidator.isValid("safe_path_272/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_272/../traversal"));
    }
    @Test
    public void testPathSafetyCase_273() {
        assertTrue(PathSafetyValidator.isValid("safe_path_273/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_273/../traversal"));
    }
    @Test
    public void testPathSafetyCase_274() {
        assertTrue(PathSafetyValidator.isValid("safe_path_274/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_274/../traversal"));
    }
    @Test
    public void testPathSafetyCase_275() {
        assertTrue(PathSafetyValidator.isValid("safe_path_275/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_275/../traversal"));
    }
    @Test
    public void testPathSafetyCase_276() {
        assertTrue(PathSafetyValidator.isValid("safe_path_276/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_276/../traversal"));
    }
    @Test
    public void testPathSafetyCase_277() {
        assertTrue(PathSafetyValidator.isValid("safe_path_277/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_277/../traversal"));
    }
    @Test
    public void testPathSafetyCase_278() {
        assertTrue(PathSafetyValidator.isValid("safe_path_278/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_278/../traversal"));
    }
    @Test
    public void testPathSafetyCase_279() {
        assertTrue(PathSafetyValidator.isValid("safe_path_279/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_279/../traversal"));
    }
    @Test
    public void testPathSafetyCase_280() {
        assertTrue(PathSafetyValidator.isValid("safe_path_280/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_280/../traversal"));
    }
    @Test
    public void testPathSafetyCase_281() {
        assertTrue(PathSafetyValidator.isValid("safe_path_281/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_281/../traversal"));
    }
    @Test
    public void testPathSafetyCase_282() {
        assertTrue(PathSafetyValidator.isValid("safe_path_282/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_282/../traversal"));
    }
    @Test
    public void testPathSafetyCase_283() {
        assertTrue(PathSafetyValidator.isValid("safe_path_283/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_283/../traversal"));
    }
    @Test
    public void testPathSafetyCase_284() {
        assertTrue(PathSafetyValidator.isValid("safe_path_284/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_284/../traversal"));
    }
    @Test
    public void testPathSafetyCase_285() {
        assertTrue(PathSafetyValidator.isValid("safe_path_285/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_285/../traversal"));
    }
    @Test
    public void testPathSafetyCase_286() {
        assertTrue(PathSafetyValidator.isValid("safe_path_286/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_286/../traversal"));
    }
    @Test
    public void testPathSafetyCase_287() {
        assertTrue(PathSafetyValidator.isValid("safe_path_287/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_287/../traversal"));
    }
    @Test
    public void testPathSafetyCase_288() {
        assertTrue(PathSafetyValidator.isValid("safe_path_288/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_288/../traversal"));
    }
    @Test
    public void testPathSafetyCase_289() {
        assertTrue(PathSafetyValidator.isValid("safe_path_289/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_289/../traversal"));
    }
    @Test
    public void testPathSafetyCase_290() {
        assertTrue(PathSafetyValidator.isValid("safe_path_290/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_290/../traversal"));
    }
    @Test
    public void testPathSafetyCase_291() {
        assertTrue(PathSafetyValidator.isValid("safe_path_291/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_291/../traversal"));
    }
    @Test
    public void testPathSafetyCase_292() {
        assertTrue(PathSafetyValidator.isValid("safe_path_292/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_292/../traversal"));
    }
    @Test
    public void testPathSafetyCase_293() {
        assertTrue(PathSafetyValidator.isValid("safe_path_293/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_293/../traversal"));
    }
    @Test
    public void testPathSafetyCase_294() {
        assertTrue(PathSafetyValidator.isValid("safe_path_294/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_294/../traversal"));
    }
    @Test
    public void testPathSafetyCase_295() {
        assertTrue(PathSafetyValidator.isValid("safe_path_295/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_295/../traversal"));
    }
    @Test
    public void testPathSafetyCase_296() {
        assertTrue(PathSafetyValidator.isValid("safe_path_296/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_296/../traversal"));
    }
    @Test
    public void testPathSafetyCase_297() {
        assertTrue(PathSafetyValidator.isValid("safe_path_297/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_297/../traversal"));
    }
    @Test
    public void testPathSafetyCase_298() {
        assertTrue(PathSafetyValidator.isValid("safe_path_298/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_298/../traversal"));
    }
    @Test
    public void testPathSafetyCase_299() {
        assertTrue(PathSafetyValidator.isValid("safe_path_299/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_299/../traversal"));
    }
    @Test
    public void testPathSafetyCase_300() {
        assertTrue(PathSafetyValidator.isValid("safe_path_300/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_300/../traversal"));
    }
    @Test
    public void testPathSafetyCase_301() {
        assertTrue(PathSafetyValidator.isValid("safe_path_301/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_301/../traversal"));
    }
    @Test
    public void testPathSafetyCase_302() {
        assertTrue(PathSafetyValidator.isValid("safe_path_302/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_302/../traversal"));
    }
    @Test
    public void testPathSafetyCase_303() {
        assertTrue(PathSafetyValidator.isValid("safe_path_303/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_303/../traversal"));
    }
    @Test
    public void testPathSafetyCase_304() {
        assertTrue(PathSafetyValidator.isValid("safe_path_304/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_304/../traversal"));
    }
    @Test
    public void testPathSafetyCase_305() {
        assertTrue(PathSafetyValidator.isValid("safe_path_305/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_305/../traversal"));
    }
    @Test
    public void testPathSafetyCase_306() {
        assertTrue(PathSafetyValidator.isValid("safe_path_306/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_306/../traversal"));
    }
    @Test
    public void testPathSafetyCase_307() {
        assertTrue(PathSafetyValidator.isValid("safe_path_307/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_307/../traversal"));
    }
    @Test
    public void testPathSafetyCase_308() {
        assertTrue(PathSafetyValidator.isValid("safe_path_308/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_308/../traversal"));
    }
    @Test
    public void testPathSafetyCase_309() {
        assertTrue(PathSafetyValidator.isValid("safe_path_309/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_309/../traversal"));
    }
    @Test
    public void testPathSafetyCase_310() {
        assertTrue(PathSafetyValidator.isValid("safe_path_310/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_310/../traversal"));
    }
    @Test
    public void testPathSafetyCase_311() {
        assertTrue(PathSafetyValidator.isValid("safe_path_311/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_311/../traversal"));
    }
    @Test
    public void testPathSafetyCase_312() {
        assertTrue(PathSafetyValidator.isValid("safe_path_312/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_312/../traversal"));
    }
    @Test
    public void testPathSafetyCase_313() {
        assertTrue(PathSafetyValidator.isValid("safe_path_313/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_313/../traversal"));
    }
    @Test
    public void testPathSafetyCase_314() {
        assertTrue(PathSafetyValidator.isValid("safe_path_314/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_314/../traversal"));
    }
    @Test
    public void testPathSafetyCase_315() {
        assertTrue(PathSafetyValidator.isValid("safe_path_315/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_315/../traversal"));
    }
    @Test
    public void testPathSafetyCase_316() {
        assertTrue(PathSafetyValidator.isValid("safe_path_316/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_316/../traversal"));
    }
    @Test
    public void testPathSafetyCase_317() {
        assertTrue(PathSafetyValidator.isValid("safe_path_317/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_317/../traversal"));
    }
    @Test
    public void testPathSafetyCase_318() {
        assertTrue(PathSafetyValidator.isValid("safe_path_318/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_318/../traversal"));
    }
    @Test
    public void testPathSafetyCase_319() {
        assertTrue(PathSafetyValidator.isValid("safe_path_319/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_319/../traversal"));
    }
    @Test
    public void testPathSafetyCase_320() {
        assertTrue(PathSafetyValidator.isValid("safe_path_320/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_320/../traversal"));
    }
    @Test
    public void testPathSafetyCase_321() {
        assertTrue(PathSafetyValidator.isValid("safe_path_321/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_321/../traversal"));
    }
    @Test
    public void testPathSafetyCase_322() {
        assertTrue(PathSafetyValidator.isValid("safe_path_322/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_322/../traversal"));
    }
    @Test
    public void testPathSafetyCase_323() {
        assertTrue(PathSafetyValidator.isValid("safe_path_323/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_323/../traversal"));
    }
    @Test
    public void testPathSafetyCase_324() {
        assertTrue(PathSafetyValidator.isValid("safe_path_324/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_324/../traversal"));
    }
    @Test
    public void testPathSafetyCase_325() {
        assertTrue(PathSafetyValidator.isValid("safe_path_325/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_325/../traversal"));
    }
    @Test
    public void testPathSafetyCase_326() {
        assertTrue(PathSafetyValidator.isValid("safe_path_326/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_326/../traversal"));
    }
    @Test
    public void testPathSafetyCase_327() {
        assertTrue(PathSafetyValidator.isValid("safe_path_327/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_327/../traversal"));
    }
    @Test
    public void testPathSafetyCase_328() {
        assertTrue(PathSafetyValidator.isValid("safe_path_328/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_328/../traversal"));
    }
    @Test
    public void testPathSafetyCase_329() {
        assertTrue(PathSafetyValidator.isValid("safe_path_329/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_329/../traversal"));
    }
    @Test
    public void testPathSafetyCase_330() {
        assertTrue(PathSafetyValidator.isValid("safe_path_330/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_330/../traversal"));
    }
    @Test
    public void testPathSafetyCase_331() {
        assertTrue(PathSafetyValidator.isValid("safe_path_331/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_331/../traversal"));
    }
    @Test
    public void testPathSafetyCase_332() {
        assertTrue(PathSafetyValidator.isValid("safe_path_332/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_332/../traversal"));
    }
    @Test
    public void testPathSafetyCase_333() {
        assertTrue(PathSafetyValidator.isValid("safe_path_333/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_333/../traversal"));
    }
    @Test
    public void testPathSafetyCase_334() {
        assertTrue(PathSafetyValidator.isValid("safe_path_334/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_334/../traversal"));
    }
    @Test
    public void testPathSafetyCase_335() {
        assertTrue(PathSafetyValidator.isValid("safe_path_335/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_335/../traversal"));
    }
    @Test
    public void testPathSafetyCase_336() {
        assertTrue(PathSafetyValidator.isValid("safe_path_336/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_336/../traversal"));
    }
    @Test
    public void testPathSafetyCase_337() {
        assertTrue(PathSafetyValidator.isValid("safe_path_337/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_337/../traversal"));
    }
    @Test
    public void testPathSafetyCase_338() {
        assertTrue(PathSafetyValidator.isValid("safe_path_338/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_338/../traversal"));
    }
    @Test
    public void testPathSafetyCase_339() {
        assertTrue(PathSafetyValidator.isValid("safe_path_339/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_339/../traversal"));
    }
    @Test
    public void testPathSafetyCase_340() {
        assertTrue(PathSafetyValidator.isValid("safe_path_340/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_340/../traversal"));
    }
    @Test
    public void testPathSafetyCase_341() {
        assertTrue(PathSafetyValidator.isValid("safe_path_341/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_341/../traversal"));
    }
    @Test
    public void testPathSafetyCase_342() {
        assertTrue(PathSafetyValidator.isValid("safe_path_342/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_342/../traversal"));
    }
    @Test
    public void testPathSafetyCase_343() {
        assertTrue(PathSafetyValidator.isValid("safe_path_343/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_343/../traversal"));
    }
    @Test
    public void testPathSafetyCase_344() {
        assertTrue(PathSafetyValidator.isValid("safe_path_344/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_344/../traversal"));
    }
    @Test
    public void testPathSafetyCase_345() {
        assertTrue(PathSafetyValidator.isValid("safe_path_345/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_345/../traversal"));
    }
    @Test
    public void testPathSafetyCase_346() {
        assertTrue(PathSafetyValidator.isValid("safe_path_346/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_346/../traversal"));
    }
    @Test
    public void testPathSafetyCase_347() {
        assertTrue(PathSafetyValidator.isValid("safe_path_347/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_347/../traversal"));
    }
    @Test
    public void testPathSafetyCase_348() {
        assertTrue(PathSafetyValidator.isValid("safe_path_348/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_348/../traversal"));
    }
    @Test
    public void testPathSafetyCase_349() {
        assertTrue(PathSafetyValidator.isValid("safe_path_349/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_349/../traversal"));
    }
    @Test
    public void testPathSafetyCase_350() {
        assertTrue(PathSafetyValidator.isValid("safe_path_350/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_350/../traversal"));
    }
    @Test
    public void testPathSafetyCase_351() {
        assertTrue(PathSafetyValidator.isValid("safe_path_351/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_351/../traversal"));
    }
    @Test
    public void testPathSafetyCase_352() {
        assertTrue(PathSafetyValidator.isValid("safe_path_352/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_352/../traversal"));
    }
    @Test
    public void testPathSafetyCase_353() {
        assertTrue(PathSafetyValidator.isValid("safe_path_353/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_353/../traversal"));
    }
    @Test
    public void testPathSafetyCase_354() {
        assertTrue(PathSafetyValidator.isValid("safe_path_354/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_354/../traversal"));
    }
    @Test
    public void testPathSafetyCase_355() {
        assertTrue(PathSafetyValidator.isValid("safe_path_355/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_355/../traversal"));
    }
    @Test
    public void testPathSafetyCase_356() {
        assertTrue(PathSafetyValidator.isValid("safe_path_356/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_356/../traversal"));
    }
    @Test
    public void testPathSafetyCase_357() {
        assertTrue(PathSafetyValidator.isValid("safe_path_357/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_357/../traversal"));
    }
    @Test
    public void testPathSafetyCase_358() {
        assertTrue(PathSafetyValidator.isValid("safe_path_358/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_358/../traversal"));
    }
    @Test
    public void testPathSafetyCase_359() {
        assertTrue(PathSafetyValidator.isValid("safe_path_359/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_359/../traversal"));
    }
    @Test
    public void testPathSafetyCase_360() {
        assertTrue(PathSafetyValidator.isValid("safe_path_360/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_360/../traversal"));
    }
    @Test
    public void testPathSafetyCase_361() {
        assertTrue(PathSafetyValidator.isValid("safe_path_361/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_361/../traversal"));
    }
    @Test
    public void testPathSafetyCase_362() {
        assertTrue(PathSafetyValidator.isValid("safe_path_362/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_362/../traversal"));
    }
    @Test
    public void testPathSafetyCase_363() {
        assertTrue(PathSafetyValidator.isValid("safe_path_363/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_363/../traversal"));
    }
    @Test
    public void testPathSafetyCase_364() {
        assertTrue(PathSafetyValidator.isValid("safe_path_364/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_364/../traversal"));
    }
    @Test
    public void testPathSafetyCase_365() {
        assertTrue(PathSafetyValidator.isValid("safe_path_365/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_365/../traversal"));
    }
    @Test
    public void testPathSafetyCase_366() {
        assertTrue(PathSafetyValidator.isValid("safe_path_366/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_366/../traversal"));
    }
    @Test
    public void testPathSafetyCase_367() {
        assertTrue(PathSafetyValidator.isValid("safe_path_367/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_367/../traversal"));
    }
    @Test
    public void testPathSafetyCase_368() {
        assertTrue(PathSafetyValidator.isValid("safe_path_368/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_368/../traversal"));
    }
    @Test
    public void testPathSafetyCase_369() {
        assertTrue(PathSafetyValidator.isValid("safe_path_369/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_369/../traversal"));
    }
    @Test
    public void testPathSafetyCase_370() {
        assertTrue(PathSafetyValidator.isValid("safe_path_370/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_370/../traversal"));
    }
    @Test
    public void testPathSafetyCase_371() {
        assertTrue(PathSafetyValidator.isValid("safe_path_371/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_371/../traversal"));
    }
    @Test
    public void testPathSafetyCase_372() {
        assertTrue(PathSafetyValidator.isValid("safe_path_372/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_372/../traversal"));
    }
    @Test
    public void testPathSafetyCase_373() {
        assertTrue(PathSafetyValidator.isValid("safe_path_373/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_373/../traversal"));
    }
    @Test
    public void testPathSafetyCase_374() {
        assertTrue(PathSafetyValidator.isValid("safe_path_374/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_374/../traversal"));
    }
    @Test
    public void testPathSafetyCase_375() {
        assertTrue(PathSafetyValidator.isValid("safe_path_375/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_375/../traversal"));
    }
    @Test
    public void testPathSafetyCase_376() {
        assertTrue(PathSafetyValidator.isValid("safe_path_376/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_376/../traversal"));
    }
    @Test
    public void testPathSafetyCase_377() {
        assertTrue(PathSafetyValidator.isValid("safe_path_377/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_377/../traversal"));
    }
    @Test
    public void testPathSafetyCase_378() {
        assertTrue(PathSafetyValidator.isValid("safe_path_378/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_378/../traversal"));
    }
    @Test
    public void testPathSafetyCase_379() {
        assertTrue(PathSafetyValidator.isValid("safe_path_379/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_379/../traversal"));
    }
    @Test
    public void testPathSafetyCase_380() {
        assertTrue(PathSafetyValidator.isValid("safe_path_380/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_380/../traversal"));
    }
    @Test
    public void testPathSafetyCase_381() {
        assertTrue(PathSafetyValidator.isValid("safe_path_381/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_381/../traversal"));
    }
    @Test
    public void testPathSafetyCase_382() {
        assertTrue(PathSafetyValidator.isValid("safe_path_382/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_382/../traversal"));
    }
    @Test
    public void testPathSafetyCase_383() {
        assertTrue(PathSafetyValidator.isValid("safe_path_383/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_383/../traversal"));
    }
    @Test
    public void testPathSafetyCase_384() {
        assertTrue(PathSafetyValidator.isValid("safe_path_384/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_384/../traversal"));
    }
    @Test
    public void testPathSafetyCase_385() {
        assertTrue(PathSafetyValidator.isValid("safe_path_385/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_385/../traversal"));
    }
    @Test
    public void testPathSafetyCase_386() {
        assertTrue(PathSafetyValidator.isValid("safe_path_386/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_386/../traversal"));
    }
    @Test
    public void testPathSafetyCase_387() {
        assertTrue(PathSafetyValidator.isValid("safe_path_387/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_387/../traversal"));
    }
    @Test
    public void testPathSafetyCase_388() {
        assertTrue(PathSafetyValidator.isValid("safe_path_388/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_388/../traversal"));
    }
    @Test
    public void testPathSafetyCase_389() {
        assertTrue(PathSafetyValidator.isValid("safe_path_389/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_389/../traversal"));
    }
    @Test
    public void testPathSafetyCase_390() {
        assertTrue(PathSafetyValidator.isValid("safe_path_390/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_390/../traversal"));
    }
    @Test
    public void testPathSafetyCase_391() {
        assertTrue(PathSafetyValidator.isValid("safe_path_391/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_391/../traversal"));
    }
    @Test
    public void testPathSafetyCase_392() {
        assertTrue(PathSafetyValidator.isValid("safe_path_392/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_392/../traversal"));
    }
    @Test
    public void testPathSafetyCase_393() {
        assertTrue(PathSafetyValidator.isValid("safe_path_393/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_393/../traversal"));
    }
    @Test
    public void testPathSafetyCase_394() {
        assertTrue(PathSafetyValidator.isValid("safe_path_394/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_394/../traversal"));
    }
    @Test
    public void testPathSafetyCase_395() {
        assertTrue(PathSafetyValidator.isValid("safe_path_395/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_395/../traversal"));
    }
    @Test
    public void testPathSafetyCase_396() {
        assertTrue(PathSafetyValidator.isValid("safe_path_396/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_396/../traversal"));
    }
    @Test
    public void testPathSafetyCase_397() {
        assertTrue(PathSafetyValidator.isValid("safe_path_397/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_397/../traversal"));
    }
    @Test
    public void testPathSafetyCase_398() {
        assertTrue(PathSafetyValidator.isValid("safe_path_398/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_398/../traversal"));
    }
    @Test
    public void testPathSafetyCase_399() {
        assertTrue(PathSafetyValidator.isValid("safe_path_399/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_399/../traversal"));
    }
    @Test
    public void testPathSafetyCase_400() {
        assertTrue(PathSafetyValidator.isValid("safe_path_400/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_400/../traversal"));
    }
    @Test
    public void testPathSafetyCase_401() {
        assertTrue(PathSafetyValidator.isValid("safe_path_401/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_401/../traversal"));
    }
    @Test
    public void testPathSafetyCase_402() {
        assertTrue(PathSafetyValidator.isValid("safe_path_402/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_402/../traversal"));
    }
    @Test
    public void testPathSafetyCase_403() {
        assertTrue(PathSafetyValidator.isValid("safe_path_403/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_403/../traversal"));
    }
    @Test
    public void testPathSafetyCase_404() {
        assertTrue(PathSafetyValidator.isValid("safe_path_404/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_404/../traversal"));
    }
    @Test
    public void testPathSafetyCase_405() {
        assertTrue(PathSafetyValidator.isValid("safe_path_405/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_405/../traversal"));
    }
    @Test
    public void testPathSafetyCase_406() {
        assertTrue(PathSafetyValidator.isValid("safe_path_406/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_406/../traversal"));
    }
    @Test
    public void testPathSafetyCase_407() {
        assertTrue(PathSafetyValidator.isValid("safe_path_407/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_407/../traversal"));
    }
    @Test
    public void testPathSafetyCase_408() {
        assertTrue(PathSafetyValidator.isValid("safe_path_408/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_408/../traversal"));
    }
    @Test
    public void testPathSafetyCase_409() {
        assertTrue(PathSafetyValidator.isValid("safe_path_409/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_409/../traversal"));
    }
    @Test
    public void testPathSafetyCase_410() {
        assertTrue(PathSafetyValidator.isValid("safe_path_410/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_410/../traversal"));
    }
    @Test
    public void testPathSafetyCase_411() {
        assertTrue(PathSafetyValidator.isValid("safe_path_411/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_411/../traversal"));
    }
    @Test
    public void testPathSafetyCase_412() {
        assertTrue(PathSafetyValidator.isValid("safe_path_412/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_412/../traversal"));
    }
    @Test
    public void testPathSafetyCase_413() {
        assertTrue(PathSafetyValidator.isValid("safe_path_413/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_413/../traversal"));
    }
    @Test
    public void testPathSafetyCase_414() {
        assertTrue(PathSafetyValidator.isValid("safe_path_414/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_414/../traversal"));
    }
    @Test
    public void testPathSafetyCase_415() {
        assertTrue(PathSafetyValidator.isValid("safe_path_415/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_415/../traversal"));
    }
    @Test
    public void testPathSafetyCase_416() {
        assertTrue(PathSafetyValidator.isValid("safe_path_416/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_416/../traversal"));
    }
    @Test
    public void testPathSafetyCase_417() {
        assertTrue(PathSafetyValidator.isValid("safe_path_417/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_417/../traversal"));
    }
    @Test
    public void testPathSafetyCase_418() {
        assertTrue(PathSafetyValidator.isValid("safe_path_418/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_418/../traversal"));
    }
    @Test
    public void testPathSafetyCase_419() {
        assertTrue(PathSafetyValidator.isValid("safe_path_419/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_419/../traversal"));
    }
    @Test
    public void testPathSafetyCase_420() {
        assertTrue(PathSafetyValidator.isValid("safe_path_420/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_420/../traversal"));
    }
    @Test
    public void testPathSafetyCase_421() {
        assertTrue(PathSafetyValidator.isValid("safe_path_421/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_421/../traversal"));
    }
    @Test
    public void testPathSafetyCase_422() {
        assertTrue(PathSafetyValidator.isValid("safe_path_422/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_422/../traversal"));
    }
    @Test
    public void testPathSafetyCase_423() {
        assertTrue(PathSafetyValidator.isValid("safe_path_423/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_423/../traversal"));
    }
    @Test
    public void testPathSafetyCase_424() {
        assertTrue(PathSafetyValidator.isValid("safe_path_424/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_424/../traversal"));
    }
    @Test
    public void testPathSafetyCase_425() {
        assertTrue(PathSafetyValidator.isValid("safe_path_425/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_425/../traversal"));
    }
    @Test
    public void testPathSafetyCase_426() {
        assertTrue(PathSafetyValidator.isValid("safe_path_426/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_426/../traversal"));
    }
    @Test
    public void testPathSafetyCase_427() {
        assertTrue(PathSafetyValidator.isValid("safe_path_427/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_427/../traversal"));
    }
    @Test
    public void testPathSafetyCase_428() {
        assertTrue(PathSafetyValidator.isValid("safe_path_428/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_428/../traversal"));
    }
    @Test
    public void testPathSafetyCase_429() {
        assertTrue(PathSafetyValidator.isValid("safe_path_429/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_429/../traversal"));
    }
    @Test
    public void testPathSafetyCase_430() {
        assertTrue(PathSafetyValidator.isValid("safe_path_430/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_430/../traversal"));
    }
    @Test
    public void testPathSafetyCase_431() {
        assertTrue(PathSafetyValidator.isValid("safe_path_431/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_431/../traversal"));
    }
    @Test
    public void testPathSafetyCase_432() {
        assertTrue(PathSafetyValidator.isValid("safe_path_432/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_432/../traversal"));
    }
    @Test
    public void testPathSafetyCase_433() {
        assertTrue(PathSafetyValidator.isValid("safe_path_433/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_433/../traversal"));
    }
    @Test
    public void testPathSafetyCase_434() {
        assertTrue(PathSafetyValidator.isValid("safe_path_434/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_434/../traversal"));
    }
    @Test
    public void testPathSafetyCase_435() {
        assertTrue(PathSafetyValidator.isValid("safe_path_435/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_435/../traversal"));
    }
    @Test
    public void testPathSafetyCase_436() {
        assertTrue(PathSafetyValidator.isValid("safe_path_436/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_436/../traversal"));
    }
    @Test
    public void testPathSafetyCase_437() {
        assertTrue(PathSafetyValidator.isValid("safe_path_437/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_437/../traversal"));
    }
    @Test
    public void testPathSafetyCase_438() {
        assertTrue(PathSafetyValidator.isValid("safe_path_438/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_438/../traversal"));
    }
    @Test
    public void testPathSafetyCase_439() {
        assertTrue(PathSafetyValidator.isValid("safe_path_439/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_439/../traversal"));
    }
    @Test
    public void testPathSafetyCase_440() {
        assertTrue(PathSafetyValidator.isValid("safe_path_440/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_440/../traversal"));
    }
    @Test
    public void testPathSafetyCase_441() {
        assertTrue(PathSafetyValidator.isValid("safe_path_441/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_441/../traversal"));
    }
    @Test
    public void testPathSafetyCase_442() {
        assertTrue(PathSafetyValidator.isValid("safe_path_442/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_442/../traversal"));
    }
    @Test
    public void testPathSafetyCase_443() {
        assertTrue(PathSafetyValidator.isValid("safe_path_443/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_443/../traversal"));
    }
    @Test
    public void testPathSafetyCase_444() {
        assertTrue(PathSafetyValidator.isValid("safe_path_444/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_444/../traversal"));
    }
    @Test
    public void testPathSafetyCase_445() {
        assertTrue(PathSafetyValidator.isValid("safe_path_445/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_445/../traversal"));
    }
    @Test
    public void testPathSafetyCase_446() {
        assertTrue(PathSafetyValidator.isValid("safe_path_446/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_446/../traversal"));
    }
    @Test
    public void testPathSafetyCase_447() {
        assertTrue(PathSafetyValidator.isValid("safe_path_447/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_447/../traversal"));
    }
    @Test
    public void testPathSafetyCase_448() {
        assertTrue(PathSafetyValidator.isValid("safe_path_448/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_448/../traversal"));
    }
    @Test
    public void testPathSafetyCase_449() {
        assertTrue(PathSafetyValidator.isValid("safe_path_449/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_449/../traversal"));
    }
    @Test
    public void testPathSafetyCase_450() {
        assertTrue(PathSafetyValidator.isValid("safe_path_450/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_450/../traversal"));
    }
    @Test
    public void testPathSafetyCase_451() {
        assertTrue(PathSafetyValidator.isValid("safe_path_451/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_451/../traversal"));
    }
    @Test
    public void testPathSafetyCase_452() {
        assertTrue(PathSafetyValidator.isValid("safe_path_452/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_452/../traversal"));
    }
    @Test
    public void testPathSafetyCase_453() {
        assertTrue(PathSafetyValidator.isValid("safe_path_453/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_453/../traversal"));
    }
    @Test
    public void testPathSafetyCase_454() {
        assertTrue(PathSafetyValidator.isValid("safe_path_454/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_454/../traversal"));
    }
    @Test
    public void testPathSafetyCase_455() {
        assertTrue(PathSafetyValidator.isValid("safe_path_455/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_455/../traversal"));
    }
    @Test
    public void testPathSafetyCase_456() {
        assertTrue(PathSafetyValidator.isValid("safe_path_456/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_456/../traversal"));
    }
    @Test
    public void testPathSafetyCase_457() {
        assertTrue(PathSafetyValidator.isValid("safe_path_457/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_457/../traversal"));
    }
    @Test
    public void testPathSafetyCase_458() {
        assertTrue(PathSafetyValidator.isValid("safe_path_458/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_458/../traversal"));
    }
    @Test
    public void testPathSafetyCase_459() {
        assertTrue(PathSafetyValidator.isValid("safe_path_459/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_459/../traversal"));
    }
    @Test
    public void testPathSafetyCase_460() {
        assertTrue(PathSafetyValidator.isValid("safe_path_460/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_460/../traversal"));
    }
    @Test
    public void testPathSafetyCase_461() {
        assertTrue(PathSafetyValidator.isValid("safe_path_461/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_461/../traversal"));
    }
    @Test
    public void testPathSafetyCase_462() {
        assertTrue(PathSafetyValidator.isValid("safe_path_462/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_462/../traversal"));
    }
    @Test
    public void testPathSafetyCase_463() {
        assertTrue(PathSafetyValidator.isValid("safe_path_463/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_463/../traversal"));
    }
    @Test
    public void testPathSafetyCase_464() {
        assertTrue(PathSafetyValidator.isValid("safe_path_464/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_464/../traversal"));
    }
    @Test
    public void testPathSafetyCase_465() {
        assertTrue(PathSafetyValidator.isValid("safe_path_465/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_465/../traversal"));
    }
    @Test
    public void testPathSafetyCase_466() {
        assertTrue(PathSafetyValidator.isValid("safe_path_466/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_466/../traversal"));
    }
    @Test
    public void testPathSafetyCase_467() {
        assertTrue(PathSafetyValidator.isValid("safe_path_467/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_467/../traversal"));
    }
    @Test
    public void testPathSafetyCase_468() {
        assertTrue(PathSafetyValidator.isValid("safe_path_468/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_468/../traversal"));
    }
    @Test
    public void testPathSafetyCase_469() {
        assertTrue(PathSafetyValidator.isValid("safe_path_469/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_469/../traversal"));
    }
    @Test
    public void testPathSafetyCase_470() {
        assertTrue(PathSafetyValidator.isValid("safe_path_470/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_470/../traversal"));
    }
    @Test
    public void testPathSafetyCase_471() {
        assertTrue(PathSafetyValidator.isValid("safe_path_471/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_471/../traversal"));
    }
    @Test
    public void testPathSafetyCase_472() {
        assertTrue(PathSafetyValidator.isValid("safe_path_472/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_472/../traversal"));
    }
    @Test
    public void testPathSafetyCase_473() {
        assertTrue(PathSafetyValidator.isValid("safe_path_473/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_473/../traversal"));
    }
    @Test
    public void testPathSafetyCase_474() {
        assertTrue(PathSafetyValidator.isValid("safe_path_474/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_474/../traversal"));
    }
    @Test
    public void testPathSafetyCase_475() {
        assertTrue(PathSafetyValidator.isValid("safe_path_475/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_475/../traversal"));
    }
    @Test
    public void testPathSafetyCase_476() {
        assertTrue(PathSafetyValidator.isValid("safe_path_476/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_476/../traversal"));
    }
    @Test
    public void testPathSafetyCase_477() {
        assertTrue(PathSafetyValidator.isValid("safe_path_477/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_477/../traversal"));
    }
    @Test
    public void testPathSafetyCase_478() {
        assertTrue(PathSafetyValidator.isValid("safe_path_478/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_478/../traversal"));
    }
    @Test
    public void testPathSafetyCase_479() {
        assertTrue(PathSafetyValidator.isValid("safe_path_479/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_479/../traversal"));
    }
    @Test
    public void testPathSafetyCase_480() {
        assertTrue(PathSafetyValidator.isValid("safe_path_480/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_480/../traversal"));
    }
    @Test
    public void testPathSafetyCase_481() {
        assertTrue(PathSafetyValidator.isValid("safe_path_481/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_481/../traversal"));
    }
    @Test
    public void testPathSafetyCase_482() {
        assertTrue(PathSafetyValidator.isValid("safe_path_482/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_482/../traversal"));
    }
    @Test
    public void testPathSafetyCase_483() {
        assertTrue(PathSafetyValidator.isValid("safe_path_483/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_483/../traversal"));
    }
    @Test
    public void testPathSafetyCase_484() {
        assertTrue(PathSafetyValidator.isValid("safe_path_484/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_484/../traversal"));
    }
    @Test
    public void testPathSafetyCase_485() {
        assertTrue(PathSafetyValidator.isValid("safe_path_485/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_485/../traversal"));
    }
    @Test
    public void testPathSafetyCase_486() {
        assertTrue(PathSafetyValidator.isValid("safe_path_486/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_486/../traversal"));
    }
    @Test
    public void testPathSafetyCase_487() {
        assertTrue(PathSafetyValidator.isValid("safe_path_487/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_487/../traversal"));
    }
    @Test
    public void testPathSafetyCase_488() {
        assertTrue(PathSafetyValidator.isValid("safe_path_488/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_488/../traversal"));
    }
    @Test
    public void testPathSafetyCase_489() {
        assertTrue(PathSafetyValidator.isValid("safe_path_489/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_489/../traversal"));
    }
    @Test
    public void testPathSafetyCase_490() {
        assertTrue(PathSafetyValidator.isValid("safe_path_490/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_490/../traversal"));
    }
    @Test
    public void testPathSafetyCase_491() {
        assertTrue(PathSafetyValidator.isValid("safe_path_491/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_491/../traversal"));
    }
    @Test
    public void testPathSafetyCase_492() {
        assertTrue(PathSafetyValidator.isValid("safe_path_492/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_492/../traversal"));
    }
    @Test
    public void testPathSafetyCase_493() {
        assertTrue(PathSafetyValidator.isValid("safe_path_493/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_493/../traversal"));
    }
    @Test
    public void testPathSafetyCase_494() {
        assertTrue(PathSafetyValidator.isValid("safe_path_494/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_494/../traversal"));
    }
    @Test
    public void testPathSafetyCase_495() {
        assertTrue(PathSafetyValidator.isValid("safe_path_495/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_495/../traversal"));
    }
    @Test
    public void testPathSafetyCase_496() {
        assertTrue(PathSafetyValidator.isValid("safe_path_496/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_496/../traversal"));
    }
    @Test
    public void testPathSafetyCase_497() {
        assertTrue(PathSafetyValidator.isValid("safe_path_497/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_497/../traversal"));
    }
    @Test
    public void testPathSafetyCase_498() {
        assertTrue(PathSafetyValidator.isValid("safe_path_498/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_498/../traversal"));
    }
    @Test
    public void testPathSafetyCase_499() {
        assertTrue(PathSafetyValidator.isValid("safe_path_499/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_499/../traversal"));
    }
    @Test
    public void testPathSafetyCase_500() {
        assertTrue(PathSafetyValidator.isValid("safe_path_500/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_500/../traversal"));
    }
    @Test
    public void testPathSafetyCase_501() {
        assertTrue(PathSafetyValidator.isValid("safe_path_501/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_501/../traversal"));
    }
    @Test
    public void testPathSafetyCase_502() {
        assertTrue(PathSafetyValidator.isValid("safe_path_502/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_502/../traversal"));
    }
    @Test
    public void testPathSafetyCase_503() {
        assertTrue(PathSafetyValidator.isValid("safe_path_503/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_503/../traversal"));
    }
    @Test
    public void testPathSafetyCase_504() {
        assertTrue(PathSafetyValidator.isValid("safe_path_504/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_504/../traversal"));
    }
    @Test
    public void testPathSafetyCase_505() {
        assertTrue(PathSafetyValidator.isValid("safe_path_505/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_505/../traversal"));
    }
    @Test
    public void testPathSafetyCase_506() {
        assertTrue(PathSafetyValidator.isValid("safe_path_506/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_506/../traversal"));
    }
    @Test
    public void testPathSafetyCase_507() {
        assertTrue(PathSafetyValidator.isValid("safe_path_507/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_507/../traversal"));
    }
    @Test
    public void testPathSafetyCase_508() {
        assertTrue(PathSafetyValidator.isValid("safe_path_508/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_508/../traversal"));
    }
    @Test
    public void testPathSafetyCase_509() {
        assertTrue(PathSafetyValidator.isValid("safe_path_509/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_509/../traversal"));
    }
    @Test
    public void testPathSafetyCase_510() {
        assertTrue(PathSafetyValidator.isValid("safe_path_510/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_510/../traversal"));
    }
    @Test
    public void testPathSafetyCase_511() {
        assertTrue(PathSafetyValidator.isValid("safe_path_511/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_511/../traversal"));
    }
    @Test
    public void testPathSafetyCase_512() {
        assertTrue(PathSafetyValidator.isValid("safe_path_512/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_512/../traversal"));
    }
    @Test
    public void testPathSafetyCase_513() {
        assertTrue(PathSafetyValidator.isValid("safe_path_513/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_513/../traversal"));
    }
    @Test
    public void testPathSafetyCase_514() {
        assertTrue(PathSafetyValidator.isValid("safe_path_514/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_514/../traversal"));
    }
    @Test
    public void testPathSafetyCase_515() {
        assertTrue(PathSafetyValidator.isValid("safe_path_515/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_515/../traversal"));
    }
    @Test
    public void testPathSafetyCase_516() {
        assertTrue(PathSafetyValidator.isValid("safe_path_516/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_516/../traversal"));
    }
    @Test
    public void testPathSafetyCase_517() {
        assertTrue(PathSafetyValidator.isValid("safe_path_517/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_517/../traversal"));
    }
    @Test
    public void testPathSafetyCase_518() {
        assertTrue(PathSafetyValidator.isValid("safe_path_518/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_518/../traversal"));
    }
    @Test
    public void testPathSafetyCase_519() {
        assertTrue(PathSafetyValidator.isValid("safe_path_519/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_519/../traversal"));
    }
    @Test
    public void testPathSafetyCase_520() {
        assertTrue(PathSafetyValidator.isValid("safe_path_520/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_520/../traversal"));
    }
    @Test
    public void testPathSafetyCase_521() {
        assertTrue(PathSafetyValidator.isValid("safe_path_521/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_521/../traversal"));
    }
    @Test
    public void testPathSafetyCase_522() {
        assertTrue(PathSafetyValidator.isValid("safe_path_522/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_522/../traversal"));
    }
    @Test
    public void testPathSafetyCase_523() {
        assertTrue(PathSafetyValidator.isValid("safe_path_523/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_523/../traversal"));
    }
    @Test
    public void testPathSafetyCase_524() {
        assertTrue(PathSafetyValidator.isValid("safe_path_524/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_524/../traversal"));
    }
    @Test
    public void testPathSafetyCase_525() {
        assertTrue(PathSafetyValidator.isValid("safe_path_525/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_525/../traversal"));
    }
    @Test
    public void testPathSafetyCase_526() {
        assertTrue(PathSafetyValidator.isValid("safe_path_526/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_526/../traversal"));
    }
    @Test
    public void testPathSafetyCase_527() {
        assertTrue(PathSafetyValidator.isValid("safe_path_527/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_527/../traversal"));
    }
    @Test
    public void testPathSafetyCase_528() {
        assertTrue(PathSafetyValidator.isValid("safe_path_528/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_528/../traversal"));
    }
    @Test
    public void testPathSafetyCase_529() {
        assertTrue(PathSafetyValidator.isValid("safe_path_529/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_529/../traversal"));
    }
    @Test
    public void testPathSafetyCase_530() {
        assertTrue(PathSafetyValidator.isValid("safe_path_530/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_530/../traversal"));
    }
    @Test
    public void testPathSafetyCase_531() {
        assertTrue(PathSafetyValidator.isValid("safe_path_531/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_531/../traversal"));
    }
    @Test
    public void testPathSafetyCase_532() {
        assertTrue(PathSafetyValidator.isValid("safe_path_532/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_532/../traversal"));
    }
    @Test
    public void testPathSafetyCase_533() {
        assertTrue(PathSafetyValidator.isValid("safe_path_533/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_533/../traversal"));
    }
    @Test
    public void testPathSafetyCase_534() {
        assertTrue(PathSafetyValidator.isValid("safe_path_534/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_534/../traversal"));
    }
    @Test
    public void testPathSafetyCase_535() {
        assertTrue(PathSafetyValidator.isValid("safe_path_535/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_535/../traversal"));
    }
    @Test
    public void testPathSafetyCase_536() {
        assertTrue(PathSafetyValidator.isValid("safe_path_536/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_536/../traversal"));
    }
    @Test
    public void testPathSafetyCase_537() {
        assertTrue(PathSafetyValidator.isValid("safe_path_537/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_537/../traversal"));
    }
    @Test
    public void testPathSafetyCase_538() {
        assertTrue(PathSafetyValidator.isValid("safe_path_538/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_538/../traversal"));
    }
    @Test
    public void testPathSafetyCase_539() {
        assertTrue(PathSafetyValidator.isValid("safe_path_539/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_539/../traversal"));
    }
    @Test
    public void testPathSafetyCase_540() {
        assertTrue(PathSafetyValidator.isValid("safe_path_540/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_540/../traversal"));
    }
    @Test
    public void testPathSafetyCase_541() {
        assertTrue(PathSafetyValidator.isValid("safe_path_541/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_541/../traversal"));
    }
    @Test
    public void testPathSafetyCase_542() {
        assertTrue(PathSafetyValidator.isValid("safe_path_542/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_542/../traversal"));
    }
    @Test
    public void testPathSafetyCase_543() {
        assertTrue(PathSafetyValidator.isValid("safe_path_543/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_543/../traversal"));
    }
    @Test
    public void testPathSafetyCase_544() {
        assertTrue(PathSafetyValidator.isValid("safe_path_544/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_544/../traversal"));
    }
    @Test
    public void testPathSafetyCase_545() {
        assertTrue(PathSafetyValidator.isValid("safe_path_545/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_545/../traversal"));
    }
    @Test
    public void testPathSafetyCase_546() {
        assertTrue(PathSafetyValidator.isValid("safe_path_546/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_546/../traversal"));
    }
    @Test
    public void testPathSafetyCase_547() {
        assertTrue(PathSafetyValidator.isValid("safe_path_547/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_547/../traversal"));
    }
    @Test
    public void testPathSafetyCase_548() {
        assertTrue(PathSafetyValidator.isValid("safe_path_548/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_548/../traversal"));
    }
    @Test
    public void testPathSafetyCase_549() {
        assertTrue(PathSafetyValidator.isValid("safe_path_549/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_549/../traversal"));
    }
    @Test
    public void testPathSafetyCase_550() {
        assertTrue(PathSafetyValidator.isValid("safe_path_550/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_550/../traversal"));
    }
    @Test
    public void testPathSafetyCase_551() {
        assertTrue(PathSafetyValidator.isValid("safe_path_551/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_551/../traversal"));
    }
    @Test
    public void testPathSafetyCase_552() {
        assertTrue(PathSafetyValidator.isValid("safe_path_552/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_552/../traversal"));
    }
    @Test
    public void testPathSafetyCase_553() {
        assertTrue(PathSafetyValidator.isValid("safe_path_553/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_553/../traversal"));
    }
    @Test
    public void testPathSafetyCase_554() {
        assertTrue(PathSafetyValidator.isValid("safe_path_554/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_554/../traversal"));
    }
    @Test
    public void testPathSafetyCase_555() {
        assertTrue(PathSafetyValidator.isValid("safe_path_555/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_555/../traversal"));
    }
    @Test
    public void testPathSafetyCase_556() {
        assertTrue(PathSafetyValidator.isValid("safe_path_556/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_556/../traversal"));
    }
    @Test
    public void testPathSafetyCase_557() {
        assertTrue(PathSafetyValidator.isValid("safe_path_557/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_557/../traversal"));
    }
    @Test
    public void testPathSafetyCase_558() {
        assertTrue(PathSafetyValidator.isValid("safe_path_558/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_558/../traversal"));
    }
    @Test
    public void testPathSafetyCase_559() {
        assertTrue(PathSafetyValidator.isValid("safe_path_559/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_559/../traversal"));
    }
    @Test
    public void testPathSafetyCase_560() {
        assertTrue(PathSafetyValidator.isValid("safe_path_560/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_560/../traversal"));
    }
    @Test
    public void testPathSafetyCase_561() {
        assertTrue(PathSafetyValidator.isValid("safe_path_561/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_561/../traversal"));
    }
    @Test
    public void testPathSafetyCase_562() {
        assertTrue(PathSafetyValidator.isValid("safe_path_562/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_562/../traversal"));
    }
    @Test
    public void testPathSafetyCase_563() {
        assertTrue(PathSafetyValidator.isValid("safe_path_563/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_563/../traversal"));
    }
    @Test
    public void testPathSafetyCase_564() {
        assertTrue(PathSafetyValidator.isValid("safe_path_564/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_564/../traversal"));
    }
    @Test
    public void testPathSafetyCase_565() {
        assertTrue(PathSafetyValidator.isValid("safe_path_565/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_565/../traversal"));
    }
    @Test
    public void testPathSafetyCase_566() {
        assertTrue(PathSafetyValidator.isValid("safe_path_566/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_566/../traversal"));
    }
    @Test
    public void testPathSafetyCase_567() {
        assertTrue(PathSafetyValidator.isValid("safe_path_567/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_567/../traversal"));
    }
    @Test
    public void testPathSafetyCase_568() {
        assertTrue(PathSafetyValidator.isValid("safe_path_568/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_568/../traversal"));
    }
    @Test
    public void testPathSafetyCase_569() {
        assertTrue(PathSafetyValidator.isValid("safe_path_569/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_569/../traversal"));
    }
    @Test
    public void testPathSafetyCase_570() {
        assertTrue(PathSafetyValidator.isValid("safe_path_570/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_570/../traversal"));
    }
    @Test
    public void testPathSafetyCase_571() {
        assertTrue(PathSafetyValidator.isValid("safe_path_571/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_571/../traversal"));
    }
    @Test
    public void testPathSafetyCase_572() {
        assertTrue(PathSafetyValidator.isValid("safe_path_572/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_572/../traversal"));
    }
    @Test
    public void testPathSafetyCase_573() {
        assertTrue(PathSafetyValidator.isValid("safe_path_573/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_573/../traversal"));
    }
    @Test
    public void testPathSafetyCase_574() {
        assertTrue(PathSafetyValidator.isValid("safe_path_574/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_574/../traversal"));
    }
    @Test
    public void testPathSafetyCase_575() {
        assertTrue(PathSafetyValidator.isValid("safe_path_575/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_575/../traversal"));
    }
    @Test
    public void testPathSafetyCase_576() {
        assertTrue(PathSafetyValidator.isValid("safe_path_576/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_576/../traversal"));
    }
    @Test
    public void testPathSafetyCase_577() {
        assertTrue(PathSafetyValidator.isValid("safe_path_577/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_577/../traversal"));
    }
    @Test
    public void testPathSafetyCase_578() {
        assertTrue(PathSafetyValidator.isValid("safe_path_578/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_578/../traversal"));
    }
    @Test
    public void testPathSafetyCase_579() {
        assertTrue(PathSafetyValidator.isValid("safe_path_579/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_579/../traversal"));
    }
    @Test
    public void testPathSafetyCase_580() {
        assertTrue(PathSafetyValidator.isValid("safe_path_580/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_580/../traversal"));
    }
    @Test
    public void testPathSafetyCase_581() {
        assertTrue(PathSafetyValidator.isValid("safe_path_581/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_581/../traversal"));
    }
    @Test
    public void testPathSafetyCase_582() {
        assertTrue(PathSafetyValidator.isValid("safe_path_582/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_582/../traversal"));
    }
    @Test
    public void testPathSafetyCase_583() {
        assertTrue(PathSafetyValidator.isValid("safe_path_583/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_583/../traversal"));
    }
    @Test
    public void testPathSafetyCase_584() {
        assertTrue(PathSafetyValidator.isValid("safe_path_584/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_584/../traversal"));
    }
    @Test
    public void testPathSafetyCase_585() {
        assertTrue(PathSafetyValidator.isValid("safe_path_585/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_585/../traversal"));
    }
    @Test
    public void testPathSafetyCase_586() {
        assertTrue(PathSafetyValidator.isValid("safe_path_586/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_586/../traversal"));
    }
    @Test
    public void testPathSafetyCase_587() {
        assertTrue(PathSafetyValidator.isValid("safe_path_587/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_587/../traversal"));
    }
    @Test
    public void testPathSafetyCase_588() {
        assertTrue(PathSafetyValidator.isValid("safe_path_588/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_588/../traversal"));
    }
    @Test
    public void testPathSafetyCase_589() {
        assertTrue(PathSafetyValidator.isValid("safe_path_589/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_589/../traversal"));
    }
    @Test
    public void testPathSafetyCase_590() {
        assertTrue(PathSafetyValidator.isValid("safe_path_590/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_590/../traversal"));
    }
    @Test
    public void testPathSafetyCase_591() {
        assertTrue(PathSafetyValidator.isValid("safe_path_591/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_591/../traversal"));
    }
    @Test
    public void testPathSafetyCase_592() {
        assertTrue(PathSafetyValidator.isValid("safe_path_592/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_592/../traversal"));
    }
    @Test
    public void testPathSafetyCase_593() {
        assertTrue(PathSafetyValidator.isValid("safe_path_593/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_593/../traversal"));
    }
    @Test
    public void testPathSafetyCase_594() {
        assertTrue(PathSafetyValidator.isValid("safe_path_594/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_594/../traversal"));
    }
    @Test
    public void testPathSafetyCase_595() {
        assertTrue(PathSafetyValidator.isValid("safe_path_595/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_595/../traversal"));
    }
    @Test
    public void testPathSafetyCase_596() {
        assertTrue(PathSafetyValidator.isValid("safe_path_596/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_596/../traversal"));
    }
    @Test
    public void testPathSafetyCase_597() {
        assertTrue(PathSafetyValidator.isValid("safe_path_597/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_597/../traversal"));
    }
    @Test
    public void testPathSafetyCase_598() {
        assertTrue(PathSafetyValidator.isValid("safe_path_598/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_598/../traversal"));
    }
    @Test
    public void testPathSafetyCase_599() {
        assertTrue(PathSafetyValidator.isValid("safe_path_599/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_599/../traversal"));
    }
    @Test
    public void testPathSafetyCase_600() {
        assertTrue(PathSafetyValidator.isValid("safe_path_600/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_600/../traversal"));
    }
    @Test
    public void testPathSafetyCase_601() {
        assertTrue(PathSafetyValidator.isValid("safe_path_601/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_601/../traversal"));
    }
    @Test
    public void testPathSafetyCase_602() {
        assertTrue(PathSafetyValidator.isValid("safe_path_602/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_602/../traversal"));
    }
    @Test
    public void testPathSafetyCase_603() {
        assertTrue(PathSafetyValidator.isValid("safe_path_603/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_603/../traversal"));
    }
    @Test
    public void testPathSafetyCase_604() {
        assertTrue(PathSafetyValidator.isValid("safe_path_604/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_604/../traversal"));
    }
    @Test
    public void testPathSafetyCase_605() {
        assertTrue(PathSafetyValidator.isValid("safe_path_605/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_605/../traversal"));
    }
    @Test
    public void testPathSafetyCase_606() {
        assertTrue(PathSafetyValidator.isValid("safe_path_606/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_606/../traversal"));
    }
    @Test
    public void testPathSafetyCase_607() {
        assertTrue(PathSafetyValidator.isValid("safe_path_607/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_607/../traversal"));
    }
    @Test
    public void testPathSafetyCase_608() {
        assertTrue(PathSafetyValidator.isValid("safe_path_608/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_608/../traversal"));
    }
    @Test
    public void testPathSafetyCase_609() {
        assertTrue(PathSafetyValidator.isValid("safe_path_609/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_609/../traversal"));
    }
    @Test
    public void testPathSafetyCase_610() {
        assertTrue(PathSafetyValidator.isValid("safe_path_610/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_610/../traversal"));
    }
    @Test
    public void testPathSafetyCase_611() {
        assertTrue(PathSafetyValidator.isValid("safe_path_611/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_611/../traversal"));
    }
    @Test
    public void testPathSafetyCase_612() {
        assertTrue(PathSafetyValidator.isValid("safe_path_612/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_612/../traversal"));
    }
    @Test
    public void testPathSafetyCase_613() {
        assertTrue(PathSafetyValidator.isValid("safe_path_613/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_613/../traversal"));
    }
    @Test
    public void testPathSafetyCase_614() {
        assertTrue(PathSafetyValidator.isValid("safe_path_614/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_614/../traversal"));
    }
    @Test
    public void testPathSafetyCase_615() {
        assertTrue(PathSafetyValidator.isValid("safe_path_615/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_615/../traversal"));
    }
    @Test
    public void testPathSafetyCase_616() {
        assertTrue(PathSafetyValidator.isValid("safe_path_616/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_616/../traversal"));
    }
    @Test
    public void testPathSafetyCase_617() {
        assertTrue(PathSafetyValidator.isValid("safe_path_617/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_617/../traversal"));
    }
    @Test
    public void testPathSafetyCase_618() {
        assertTrue(PathSafetyValidator.isValid("safe_path_618/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_618/../traversal"));
    }
    @Test
    public void testPathSafetyCase_619() {
        assertTrue(PathSafetyValidator.isValid("safe_path_619/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_619/../traversal"));
    }
    @Test
    public void testPathSafetyCase_620() {
        assertTrue(PathSafetyValidator.isValid("safe_path_620/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_620/../traversal"));
    }
    @Test
    public void testPathSafetyCase_621() {
        assertTrue(PathSafetyValidator.isValid("safe_path_621/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_621/../traversal"));
    }
    @Test
    public void testPathSafetyCase_622() {
        assertTrue(PathSafetyValidator.isValid("safe_path_622/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_622/../traversal"));
    }
    @Test
    public void testPathSafetyCase_623() {
        assertTrue(PathSafetyValidator.isValid("safe_path_623/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_623/../traversal"));
    }
    @Test
    public void testPathSafetyCase_624() {
        assertTrue(PathSafetyValidator.isValid("safe_path_624/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_624/../traversal"));
    }
    @Test
    public void testPathSafetyCase_625() {
        assertTrue(PathSafetyValidator.isValid("safe_path_625/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_625/../traversal"));
    }
    @Test
    public void testPathSafetyCase_626() {
        assertTrue(PathSafetyValidator.isValid("safe_path_626/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_626/../traversal"));
    }
    @Test
    public void testPathSafetyCase_627() {
        assertTrue(PathSafetyValidator.isValid("safe_path_627/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_627/../traversal"));
    }
    @Test
    public void testPathSafetyCase_628() {
        assertTrue(PathSafetyValidator.isValid("safe_path_628/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_628/../traversal"));
    }
    @Test
    public void testPathSafetyCase_629() {
        assertTrue(PathSafetyValidator.isValid("safe_path_629/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_629/../traversal"));
    }
    @Test
    public void testPathSafetyCase_630() {
        assertTrue(PathSafetyValidator.isValid("safe_path_630/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_630/../traversal"));
    }
    @Test
    public void testPathSafetyCase_631() {
        assertTrue(PathSafetyValidator.isValid("safe_path_631/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_631/../traversal"));
    }
    @Test
    public void testPathSafetyCase_632() {
        assertTrue(PathSafetyValidator.isValid("safe_path_632/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_632/../traversal"));
    }
    @Test
    public void testPathSafetyCase_633() {
        assertTrue(PathSafetyValidator.isValid("safe_path_633/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_633/../traversal"));
    }
    @Test
    public void testPathSafetyCase_634() {
        assertTrue(PathSafetyValidator.isValid("safe_path_634/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_634/../traversal"));
    }
    @Test
    public void testPathSafetyCase_635() {
        assertTrue(PathSafetyValidator.isValid("safe_path_635/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_635/../traversal"));
    }
    @Test
    public void testPathSafetyCase_636() {
        assertTrue(PathSafetyValidator.isValid("safe_path_636/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_636/../traversal"));
    }
    @Test
    public void testPathSafetyCase_637() {
        assertTrue(PathSafetyValidator.isValid("safe_path_637/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_637/../traversal"));
    }
    @Test
    public void testPathSafetyCase_638() {
        assertTrue(PathSafetyValidator.isValid("safe_path_638/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_638/../traversal"));
    }
    @Test
    public void testPathSafetyCase_639() {
        assertTrue(PathSafetyValidator.isValid("safe_path_639/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_639/../traversal"));
    }
    @Test
    public void testPathSafetyCase_640() {
        assertTrue(PathSafetyValidator.isValid("safe_path_640/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_640/../traversal"));
    }
    @Test
    public void testPathSafetyCase_641() {
        assertTrue(PathSafetyValidator.isValid("safe_path_641/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_641/../traversal"));
    }
    @Test
    public void testPathSafetyCase_642() {
        assertTrue(PathSafetyValidator.isValid("safe_path_642/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_642/../traversal"));
    }
    @Test
    public void testPathSafetyCase_643() {
        assertTrue(PathSafetyValidator.isValid("safe_path_643/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_643/../traversal"));
    }
    @Test
    public void testPathSafetyCase_644() {
        assertTrue(PathSafetyValidator.isValid("safe_path_644/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_644/../traversal"));
    }
    @Test
    public void testPathSafetyCase_645() {
        assertTrue(PathSafetyValidator.isValid("safe_path_645/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_645/../traversal"));
    }
    @Test
    public void testPathSafetyCase_646() {
        assertTrue(PathSafetyValidator.isValid("safe_path_646/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_646/../traversal"));
    }
    @Test
    public void testPathSafetyCase_647() {
        assertTrue(PathSafetyValidator.isValid("safe_path_647/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_647/../traversal"));
    }
    @Test
    public void testPathSafetyCase_648() {
        assertTrue(PathSafetyValidator.isValid("safe_path_648/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_648/../traversal"));
    }
    @Test
    public void testPathSafetyCase_649() {
        assertTrue(PathSafetyValidator.isValid("safe_path_649/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_649/../traversal"));
    }
    @Test
    public void testPathSafetyCase_650() {
        assertTrue(PathSafetyValidator.isValid("safe_path_650/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_650/../traversal"));
    }
    @Test
    public void testPathSafetyCase_651() {
        assertTrue(PathSafetyValidator.isValid("safe_path_651/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_651/../traversal"));
    }
    @Test
    public void testPathSafetyCase_652() {
        assertTrue(PathSafetyValidator.isValid("safe_path_652/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_652/../traversal"));
    }
    @Test
    public void testPathSafetyCase_653() {
        assertTrue(PathSafetyValidator.isValid("safe_path_653/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_653/../traversal"));
    }
    @Test
    public void testPathSafetyCase_654() {
        assertTrue(PathSafetyValidator.isValid("safe_path_654/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_654/../traversal"));
    }
    @Test
    public void testPathSafetyCase_655() {
        assertTrue(PathSafetyValidator.isValid("safe_path_655/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_655/../traversal"));
    }
    @Test
    public void testPathSafetyCase_656() {
        assertTrue(PathSafetyValidator.isValid("safe_path_656/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_656/../traversal"));
    }
    @Test
    public void testPathSafetyCase_657() {
        assertTrue(PathSafetyValidator.isValid("safe_path_657/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_657/../traversal"));
    }
    @Test
    public void testPathSafetyCase_658() {
        assertTrue(PathSafetyValidator.isValid("safe_path_658/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_658/../traversal"));
    }
    @Test
    public void testPathSafetyCase_659() {
        assertTrue(PathSafetyValidator.isValid("safe_path_659/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_659/../traversal"));
    }
    @Test
    public void testPathSafetyCase_660() {
        assertTrue(PathSafetyValidator.isValid("safe_path_660/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_660/../traversal"));
    }
    @Test
    public void testPathSafetyCase_661() {
        assertTrue(PathSafetyValidator.isValid("safe_path_661/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_661/../traversal"));
    }
    @Test
    public void testPathSafetyCase_662() {
        assertTrue(PathSafetyValidator.isValid("safe_path_662/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_662/../traversal"));
    }
    @Test
    public void testPathSafetyCase_663() {
        assertTrue(PathSafetyValidator.isValid("safe_path_663/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_663/../traversal"));
    }
    @Test
    public void testPathSafetyCase_664() {
        assertTrue(PathSafetyValidator.isValid("safe_path_664/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_664/../traversal"));
    }
    @Test
    public void testPathSafetyCase_665() {
        assertTrue(PathSafetyValidator.isValid("safe_path_665/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_665/../traversal"));
    }
    @Test
    public void testPathSafetyCase_666() {
        assertTrue(PathSafetyValidator.isValid("safe_path_666/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_666/../traversal"));
    }
    @Test
    public void testPathSafetyCase_667() {
        assertTrue(PathSafetyValidator.isValid("safe_path_667/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_667/../traversal"));
    }
    @Test
    public void testPathSafetyCase_668() {
        assertTrue(PathSafetyValidator.isValid("safe_path_668/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_668/../traversal"));
    }
    @Test
    public void testPathSafetyCase_669() {
        assertTrue(PathSafetyValidator.isValid("safe_path_669/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_669/../traversal"));
    }
    @Test
    public void testPathSafetyCase_670() {
        assertTrue(PathSafetyValidator.isValid("safe_path_670/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_670/../traversal"));
    }
    @Test
    public void testPathSafetyCase_671() {
        assertTrue(PathSafetyValidator.isValid("safe_path_671/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_671/../traversal"));
    }
    @Test
    public void testPathSafetyCase_672() {
        assertTrue(PathSafetyValidator.isValid("safe_path_672/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_672/../traversal"));
    }
    @Test
    public void testPathSafetyCase_673() {
        assertTrue(PathSafetyValidator.isValid("safe_path_673/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_673/../traversal"));
    }
    @Test
    public void testPathSafetyCase_674() {
        assertTrue(PathSafetyValidator.isValid("safe_path_674/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_674/../traversal"));
    }
    @Test
    public void testPathSafetyCase_675() {
        assertTrue(PathSafetyValidator.isValid("safe_path_675/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_675/../traversal"));
    }
    @Test
    public void testPathSafetyCase_676() {
        assertTrue(PathSafetyValidator.isValid("safe_path_676/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_676/../traversal"));
    }
    @Test
    public void testPathSafetyCase_677() {
        assertTrue(PathSafetyValidator.isValid("safe_path_677/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_677/../traversal"));
    }
    @Test
    public void testPathSafetyCase_678() {
        assertTrue(PathSafetyValidator.isValid("safe_path_678/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_678/../traversal"));
    }
    @Test
    public void testPathSafetyCase_679() {
        assertTrue(PathSafetyValidator.isValid("safe_path_679/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_679/../traversal"));
    }
    @Test
    public void testPathSafetyCase_680() {
        assertTrue(PathSafetyValidator.isValid("safe_path_680/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_680/../traversal"));
    }
    @Test
    public void testPathSafetyCase_681() {
        assertTrue(PathSafetyValidator.isValid("safe_path_681/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_681/../traversal"));
    }
    @Test
    public void testPathSafetyCase_682() {
        assertTrue(PathSafetyValidator.isValid("safe_path_682/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_682/../traversal"));
    }
    @Test
    public void testPathSafetyCase_683() {
        assertTrue(PathSafetyValidator.isValid("safe_path_683/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_683/../traversal"));
    }
    @Test
    public void testPathSafetyCase_684() {
        assertTrue(PathSafetyValidator.isValid("safe_path_684/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_684/../traversal"));
    }
    @Test
    public void testPathSafetyCase_685() {
        assertTrue(PathSafetyValidator.isValid("safe_path_685/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_685/../traversal"));
    }
    @Test
    public void testPathSafetyCase_686() {
        assertTrue(PathSafetyValidator.isValid("safe_path_686/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_686/../traversal"));
    }
    @Test
    public void testPathSafetyCase_687() {
        assertTrue(PathSafetyValidator.isValid("safe_path_687/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_687/../traversal"));
    }
    @Test
    public void testPathSafetyCase_688() {
        assertTrue(PathSafetyValidator.isValid("safe_path_688/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_688/../traversal"));
    }
    @Test
    public void testPathSafetyCase_689() {
        assertTrue(PathSafetyValidator.isValid("safe_path_689/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_689/../traversal"));
    }
    @Test
    public void testPathSafetyCase_690() {
        assertTrue(PathSafetyValidator.isValid("safe_path_690/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_690/../traversal"));
    }
    @Test
    public void testPathSafetyCase_691() {
        assertTrue(PathSafetyValidator.isValid("safe_path_691/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_691/../traversal"));
    }
    @Test
    public void testPathSafetyCase_692() {
        assertTrue(PathSafetyValidator.isValid("safe_path_692/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_692/../traversal"));
    }
    @Test
    public void testPathSafetyCase_693() {
        assertTrue(PathSafetyValidator.isValid("safe_path_693/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_693/../traversal"));
    }
    @Test
    public void testPathSafetyCase_694() {
        assertTrue(PathSafetyValidator.isValid("safe_path_694/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_694/../traversal"));
    }
    @Test
    public void testPathSafetyCase_695() {
        assertTrue(PathSafetyValidator.isValid("safe_path_695/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_695/../traversal"));
    }
    @Test
    public void testPathSafetyCase_696() {
        assertTrue(PathSafetyValidator.isValid("safe_path_696/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_696/../traversal"));
    }
    @Test
    public void testPathSafetyCase_697() {
        assertTrue(PathSafetyValidator.isValid("safe_path_697/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_697/../traversal"));
    }
    @Test
    public void testPathSafetyCase_698() {
        assertTrue(PathSafetyValidator.isValid("safe_path_698/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_698/../traversal"));
    }
    @Test
    public void testPathSafetyCase_699() {
        assertTrue(PathSafetyValidator.isValid("safe_path_699/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_699/../traversal"));
    }
    @Test
    public void testPathSafetyCase_700() {
        assertTrue(PathSafetyValidator.isValid("safe_path_700/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_700/../traversal"));
    }
    @Test
    public void testPathSafetyCase_701() {
        assertTrue(PathSafetyValidator.isValid("safe_path_701/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_701/../traversal"));
    }
    @Test
    public void testPathSafetyCase_702() {
        assertTrue(PathSafetyValidator.isValid("safe_path_702/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_702/../traversal"));
    }
    @Test
    public void testPathSafetyCase_703() {
        assertTrue(PathSafetyValidator.isValid("safe_path_703/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_703/../traversal"));
    }
    @Test
    public void testPathSafetyCase_704() {
        assertTrue(PathSafetyValidator.isValid("safe_path_704/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_704/../traversal"));
    }
    @Test
    public void testPathSafetyCase_705() {
        assertTrue(PathSafetyValidator.isValid("safe_path_705/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_705/../traversal"));
    }
    @Test
    public void testPathSafetyCase_706() {
        assertTrue(PathSafetyValidator.isValid("safe_path_706/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_706/../traversal"));
    }
    @Test
    public void testPathSafetyCase_707() {
        assertTrue(PathSafetyValidator.isValid("safe_path_707/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_707/../traversal"));
    }
    @Test
    public void testPathSafetyCase_708() {
        assertTrue(PathSafetyValidator.isValid("safe_path_708/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_708/../traversal"));
    }
    @Test
    public void testPathSafetyCase_709() {
        assertTrue(PathSafetyValidator.isValid("safe_path_709/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_709/../traversal"));
    }
    @Test
    public void testPathSafetyCase_710() {
        assertTrue(PathSafetyValidator.isValid("safe_path_710/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_710/../traversal"));
    }
    @Test
    public void testPathSafetyCase_711() {
        assertTrue(PathSafetyValidator.isValid("safe_path_711/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_711/../traversal"));
    }
    @Test
    public void testPathSafetyCase_712() {
        assertTrue(PathSafetyValidator.isValid("safe_path_712/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_712/../traversal"));
    }
    @Test
    public void testPathSafetyCase_713() {
        assertTrue(PathSafetyValidator.isValid("safe_path_713/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_713/../traversal"));
    }
    @Test
    public void testPathSafetyCase_714() {
        assertTrue(PathSafetyValidator.isValid("safe_path_714/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_714/../traversal"));
    }
    @Test
    public void testPathSafetyCase_715() {
        assertTrue(PathSafetyValidator.isValid("safe_path_715/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_715/../traversal"));
    }
    @Test
    public void testPathSafetyCase_716() {
        assertTrue(PathSafetyValidator.isValid("safe_path_716/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_716/../traversal"));
    }
    @Test
    public void testPathSafetyCase_717() {
        assertTrue(PathSafetyValidator.isValid("safe_path_717/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_717/../traversal"));
    }
    @Test
    public void testPathSafetyCase_718() {
        assertTrue(PathSafetyValidator.isValid("safe_path_718/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_718/../traversal"));
    }
    @Test
    public void testPathSafetyCase_719() {
        assertTrue(PathSafetyValidator.isValid("safe_path_719/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_719/../traversal"));
    }
    @Test
    public void testPathSafetyCase_720() {
        assertTrue(PathSafetyValidator.isValid("safe_path_720/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_720/../traversal"));
    }
    @Test
    public void testPathSafetyCase_721() {
        assertTrue(PathSafetyValidator.isValid("safe_path_721/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_721/../traversal"));
    }
    @Test
    public void testPathSafetyCase_722() {
        assertTrue(PathSafetyValidator.isValid("safe_path_722/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_722/../traversal"));
    }
    @Test
    public void testPathSafetyCase_723() {
        assertTrue(PathSafetyValidator.isValid("safe_path_723/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_723/../traversal"));
    }
    @Test
    public void testPathSafetyCase_724() {
        assertTrue(PathSafetyValidator.isValid("safe_path_724/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_724/../traversal"));
    }
    @Test
    public void testPathSafetyCase_725() {
        assertTrue(PathSafetyValidator.isValid("safe_path_725/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_725/../traversal"));
    }
    @Test
    public void testPathSafetyCase_726() {
        assertTrue(PathSafetyValidator.isValid("safe_path_726/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_726/../traversal"));
    }
    @Test
    public void testPathSafetyCase_727() {
        assertTrue(PathSafetyValidator.isValid("safe_path_727/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_727/../traversal"));
    }
    @Test
    public void testPathSafetyCase_728() {
        assertTrue(PathSafetyValidator.isValid("safe_path_728/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_728/../traversal"));
    }
    @Test
    public void testPathSafetyCase_729() {
        assertTrue(PathSafetyValidator.isValid("safe_path_729/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_729/../traversal"));
    }
    @Test
    public void testPathSafetyCase_730() {
        assertTrue(PathSafetyValidator.isValid("safe_path_730/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_730/../traversal"));
    }
    @Test
    public void testPathSafetyCase_731() {
        assertTrue(PathSafetyValidator.isValid("safe_path_731/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_731/../traversal"));
    }
    @Test
    public void testPathSafetyCase_732() {
        assertTrue(PathSafetyValidator.isValid("safe_path_732/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_732/../traversal"));
    }
    @Test
    public void testPathSafetyCase_733() {
        assertTrue(PathSafetyValidator.isValid("safe_path_733/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_733/../traversal"));
    }
    @Test
    public void testPathSafetyCase_734() {
        assertTrue(PathSafetyValidator.isValid("safe_path_734/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_734/../traversal"));
    }
    @Test
    public void testPathSafetyCase_735() {
        assertTrue(PathSafetyValidator.isValid("safe_path_735/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_735/../traversal"));
    }
    @Test
    public void testPathSafetyCase_736() {
        assertTrue(PathSafetyValidator.isValid("safe_path_736/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_736/../traversal"));
    }
    @Test
    public void testPathSafetyCase_737() {
        assertTrue(PathSafetyValidator.isValid("safe_path_737/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_737/../traversal"));
    }
    @Test
    public void testPathSafetyCase_738() {
        assertTrue(PathSafetyValidator.isValid("safe_path_738/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_738/../traversal"));
    }
    @Test
    public void testPathSafetyCase_739() {
        assertTrue(PathSafetyValidator.isValid("safe_path_739/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_739/../traversal"));
    }
    @Test
    public void testPathSafetyCase_740() {
        assertTrue(PathSafetyValidator.isValid("safe_path_740/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_740/../traversal"));
    }
    @Test
    public void testPathSafetyCase_741() {
        assertTrue(PathSafetyValidator.isValid("safe_path_741/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_741/../traversal"));
    }
    @Test
    public void testPathSafetyCase_742() {
        assertTrue(PathSafetyValidator.isValid("safe_path_742/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_742/../traversal"));
    }
    @Test
    public void testPathSafetyCase_743() {
        assertTrue(PathSafetyValidator.isValid("safe_path_743/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_743/../traversal"));
    }
    @Test
    public void testPathSafetyCase_744() {
        assertTrue(PathSafetyValidator.isValid("safe_path_744/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_744/../traversal"));
    }
    @Test
    public void testPathSafetyCase_745() {
        assertTrue(PathSafetyValidator.isValid("safe_path_745/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_745/../traversal"));
    }
    @Test
    public void testPathSafetyCase_746() {
        assertTrue(PathSafetyValidator.isValid("safe_path_746/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_746/../traversal"));
    }
    @Test
    public void testPathSafetyCase_747() {
        assertTrue(PathSafetyValidator.isValid("safe_path_747/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_747/../traversal"));
    }
    @Test
    public void testPathSafetyCase_748() {
        assertTrue(PathSafetyValidator.isValid("safe_path_748/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_748/../traversal"));
    }
    @Test
    public void testPathSafetyCase_749() {
        assertTrue(PathSafetyValidator.isValid("safe_path_749/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_749/../traversal"));
    }
    @Test
    public void testPathSafetyCase_750() {
        assertTrue(PathSafetyValidator.isValid("safe_path_750/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_750/../traversal"));
    }
    @Test
    public void testPathSafetyCase_751() {
        assertTrue(PathSafetyValidator.isValid("safe_path_751/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_751/../traversal"));
    }
    @Test
    public void testPathSafetyCase_752() {
        assertTrue(PathSafetyValidator.isValid("safe_path_752/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_752/../traversal"));
    }
    @Test
    public void testPathSafetyCase_753() {
        assertTrue(PathSafetyValidator.isValid("safe_path_753/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_753/../traversal"));
    }
    @Test
    public void testPathSafetyCase_754() {
        assertTrue(PathSafetyValidator.isValid("safe_path_754/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_754/../traversal"));
    }
    @Test
    public void testPathSafetyCase_755() {
        assertTrue(PathSafetyValidator.isValid("safe_path_755/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_755/../traversal"));
    }
    @Test
    public void testPathSafetyCase_756() {
        assertTrue(PathSafetyValidator.isValid("safe_path_756/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_756/../traversal"));
    }
    @Test
    public void testPathSafetyCase_757() {
        assertTrue(PathSafetyValidator.isValid("safe_path_757/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_757/../traversal"));
    }
    @Test
    public void testPathSafetyCase_758() {
        assertTrue(PathSafetyValidator.isValid("safe_path_758/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_758/../traversal"));
    }
    @Test
    public void testPathSafetyCase_759() {
        assertTrue(PathSafetyValidator.isValid("safe_path_759/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_759/../traversal"));
    }
    @Test
    public void testPathSafetyCase_760() {
        assertTrue(PathSafetyValidator.isValid("safe_path_760/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_760/../traversal"));
    }
    @Test
    public void testPathSafetyCase_761() {
        assertTrue(PathSafetyValidator.isValid("safe_path_761/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_761/../traversal"));
    }
    @Test
    public void testPathSafetyCase_762() {
        assertTrue(PathSafetyValidator.isValid("safe_path_762/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_762/../traversal"));
    }
    @Test
    public void testPathSafetyCase_763() {
        assertTrue(PathSafetyValidator.isValid("safe_path_763/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_763/../traversal"));
    }
    @Test
    public void testPathSafetyCase_764() {
        assertTrue(PathSafetyValidator.isValid("safe_path_764/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_764/../traversal"));
    }
    @Test
    public void testPathSafetyCase_765() {
        assertTrue(PathSafetyValidator.isValid("safe_path_765/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_765/../traversal"));
    }
    @Test
    public void testPathSafetyCase_766() {
        assertTrue(PathSafetyValidator.isValid("safe_path_766/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_766/../traversal"));
    }
    @Test
    public void testPathSafetyCase_767() {
        assertTrue(PathSafetyValidator.isValid("safe_path_767/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_767/../traversal"));
    }
    @Test
    public void testPathSafetyCase_768() {
        assertTrue(PathSafetyValidator.isValid("safe_path_768/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_768/../traversal"));
    }
    @Test
    public void testPathSafetyCase_769() {
        assertTrue(PathSafetyValidator.isValid("safe_path_769/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_769/../traversal"));
    }
    @Test
    public void testPathSafetyCase_770() {
        assertTrue(PathSafetyValidator.isValid("safe_path_770/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_770/../traversal"));
    }
    @Test
    public void testPathSafetyCase_771() {
        assertTrue(PathSafetyValidator.isValid("safe_path_771/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_771/../traversal"));
    }
    @Test
    public void testPathSafetyCase_772() {
        assertTrue(PathSafetyValidator.isValid("safe_path_772/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_772/../traversal"));
    }
    @Test
    public void testPathSafetyCase_773() {
        assertTrue(PathSafetyValidator.isValid("safe_path_773/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_773/../traversal"));
    }
    @Test
    public void testPathSafetyCase_774() {
        assertTrue(PathSafetyValidator.isValid("safe_path_774/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_774/../traversal"));
    }
    @Test
    public void testPathSafetyCase_775() {
        assertTrue(PathSafetyValidator.isValid("safe_path_775/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_775/../traversal"));
    }
    @Test
    public void testPathSafetyCase_776() {
        assertTrue(PathSafetyValidator.isValid("safe_path_776/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_776/../traversal"));
    }
    @Test
    public void testPathSafetyCase_777() {
        assertTrue(PathSafetyValidator.isValid("safe_path_777/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_777/../traversal"));
    }
    @Test
    public void testPathSafetyCase_778() {
        assertTrue(PathSafetyValidator.isValid("safe_path_778/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_778/../traversal"));
    }
    @Test
    public void testPathSafetyCase_779() {
        assertTrue(PathSafetyValidator.isValid("safe_path_779/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_779/../traversal"));
    }
    @Test
    public void testPathSafetyCase_780() {
        assertTrue(PathSafetyValidator.isValid("safe_path_780/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_780/../traversal"));
    }
    @Test
    public void testPathSafetyCase_781() {
        assertTrue(PathSafetyValidator.isValid("safe_path_781/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_781/../traversal"));
    }
    @Test
    public void testPathSafetyCase_782() {
        assertTrue(PathSafetyValidator.isValid("safe_path_782/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_782/../traversal"));
    }
    @Test
    public void testPathSafetyCase_783() {
        assertTrue(PathSafetyValidator.isValid("safe_path_783/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_783/../traversal"));
    }
    @Test
    public void testPathSafetyCase_784() {
        assertTrue(PathSafetyValidator.isValid("safe_path_784/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_784/../traversal"));
    }
    @Test
    public void testPathSafetyCase_785() {
        assertTrue(PathSafetyValidator.isValid("safe_path_785/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_785/../traversal"));
    }
    @Test
    public void testPathSafetyCase_786() {
        assertTrue(PathSafetyValidator.isValid("safe_path_786/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_786/../traversal"));
    }
    @Test
    public void testPathSafetyCase_787() {
        assertTrue(PathSafetyValidator.isValid("safe_path_787/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_787/../traversal"));
    }
    @Test
    public void testPathSafetyCase_788() {
        assertTrue(PathSafetyValidator.isValid("safe_path_788/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_788/../traversal"));
    }
    @Test
    public void testPathSafetyCase_789() {
        assertTrue(PathSafetyValidator.isValid("safe_path_789/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_789/../traversal"));
    }
    @Test
    public void testPathSafetyCase_790() {
        assertTrue(PathSafetyValidator.isValid("safe_path_790/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_790/../traversal"));
    }
    @Test
    public void testPathSafetyCase_791() {
        assertTrue(PathSafetyValidator.isValid("safe_path_791/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_791/../traversal"));
    }
    @Test
    public void testPathSafetyCase_792() {
        assertTrue(PathSafetyValidator.isValid("safe_path_792/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_792/../traversal"));
    }
    @Test
    public void testPathSafetyCase_793() {
        assertTrue(PathSafetyValidator.isValid("safe_path_793/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_793/../traversal"));
    }
    @Test
    public void testPathSafetyCase_794() {
        assertTrue(PathSafetyValidator.isValid("safe_path_794/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_794/../traversal"));
    }
    @Test
    public void testPathSafetyCase_795() {
        assertTrue(PathSafetyValidator.isValid("safe_path_795/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_795/../traversal"));
    }
    @Test
    public void testPathSafetyCase_796() {
        assertTrue(PathSafetyValidator.isValid("safe_path_796/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_796/../traversal"));
    }
    @Test
    public void testPathSafetyCase_797() {
        assertTrue(PathSafetyValidator.isValid("safe_path_797/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_797/../traversal"));
    }
    @Test
    public void testPathSafetyCase_798() {
        assertTrue(PathSafetyValidator.isValid("safe_path_798/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_798/../traversal"));
    }
    @Test
    public void testPathSafetyCase_799() {
        assertTrue(PathSafetyValidator.isValid("safe_path_799/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_799/../traversal"));
    }
    @Test
    public void testPathSafetyCase_800() {
        assertTrue(PathSafetyValidator.isValid("safe_path_800/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_800/../traversal"));
    }
    @Test
    public void testPathSafetyCase_801() {
        assertTrue(PathSafetyValidator.isValid("safe_path_801/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_801/../traversal"));
    }
    @Test
    public void testPathSafetyCase_802() {
        assertTrue(PathSafetyValidator.isValid("safe_path_802/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_802/../traversal"));
    }
    @Test
    public void testPathSafetyCase_803() {
        assertTrue(PathSafetyValidator.isValid("safe_path_803/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_803/../traversal"));
    }
    @Test
    public void testPathSafetyCase_804() {
        assertTrue(PathSafetyValidator.isValid("safe_path_804/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_804/../traversal"));
    }
    @Test
    public void testPathSafetyCase_805() {
        assertTrue(PathSafetyValidator.isValid("safe_path_805/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_805/../traversal"));
    }
    @Test
    public void testPathSafetyCase_806() {
        assertTrue(PathSafetyValidator.isValid("safe_path_806/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_806/../traversal"));
    }
    @Test
    public void testPathSafetyCase_807() {
        assertTrue(PathSafetyValidator.isValid("safe_path_807/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_807/../traversal"));
    }
    @Test
    public void testPathSafetyCase_808() {
        assertTrue(PathSafetyValidator.isValid("safe_path_808/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_808/../traversal"));
    }
    @Test
    public void testPathSafetyCase_809() {
        assertTrue(PathSafetyValidator.isValid("safe_path_809/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_809/../traversal"));
    }
    @Test
    public void testPathSafetyCase_810() {
        assertTrue(PathSafetyValidator.isValid("safe_path_810/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_810/../traversal"));
    }
    @Test
    public void testPathSafetyCase_811() {
        assertTrue(PathSafetyValidator.isValid("safe_path_811/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_811/../traversal"));
    }
    @Test
    public void testPathSafetyCase_812() {
        assertTrue(PathSafetyValidator.isValid("safe_path_812/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_812/../traversal"));
    }
    @Test
    public void testPathSafetyCase_813() {
        assertTrue(PathSafetyValidator.isValid("safe_path_813/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_813/../traversal"));
    }
    @Test
    public void testPathSafetyCase_814() {
        assertTrue(PathSafetyValidator.isValid("safe_path_814/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_814/../traversal"));
    }
    @Test
    public void testPathSafetyCase_815() {
        assertTrue(PathSafetyValidator.isValid("safe_path_815/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_815/../traversal"));
    }
    @Test
    public void testPathSafetyCase_816() {
        assertTrue(PathSafetyValidator.isValid("safe_path_816/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_816/../traversal"));
    }
    @Test
    public void testPathSafetyCase_817() {
        assertTrue(PathSafetyValidator.isValid("safe_path_817/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_817/../traversal"));
    }
    @Test
    public void testPathSafetyCase_818() {
        assertTrue(PathSafetyValidator.isValid("safe_path_818/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_818/../traversal"));
    }
    @Test
    public void testPathSafetyCase_819() {
        assertTrue(PathSafetyValidator.isValid("safe_path_819/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_819/../traversal"));
    }
    @Test
    public void testPathSafetyCase_820() {
        assertTrue(PathSafetyValidator.isValid("safe_path_820/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_820/../traversal"));
    }
    @Test
    public void testPathSafetyCase_821() {
        assertTrue(PathSafetyValidator.isValid("safe_path_821/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_821/../traversal"));
    }
    @Test
    public void testPathSafetyCase_822() {
        assertTrue(PathSafetyValidator.isValid("safe_path_822/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_822/../traversal"));
    }
    @Test
    public void testPathSafetyCase_823() {
        assertTrue(PathSafetyValidator.isValid("safe_path_823/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_823/../traversal"));
    }
    @Test
    public void testPathSafetyCase_824() {
        assertTrue(PathSafetyValidator.isValid("safe_path_824/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_824/../traversal"));
    }
    @Test
    public void testPathSafetyCase_825() {
        assertTrue(PathSafetyValidator.isValid("safe_path_825/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_825/../traversal"));
    }
    @Test
    public void testPathSafetyCase_826() {
        assertTrue(PathSafetyValidator.isValid("safe_path_826/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_826/../traversal"));
    }
    @Test
    public void testPathSafetyCase_827() {
        assertTrue(PathSafetyValidator.isValid("safe_path_827/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_827/../traversal"));
    }
    @Test
    public void testPathSafetyCase_828() {
        assertTrue(PathSafetyValidator.isValid("safe_path_828/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_828/../traversal"));
    }
    @Test
    public void testPathSafetyCase_829() {
        assertTrue(PathSafetyValidator.isValid("safe_path_829/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_829/../traversal"));
    }
    @Test
    public void testPathSafetyCase_830() {
        assertTrue(PathSafetyValidator.isValid("safe_path_830/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_830/../traversal"));
    }
    @Test
    public void testPathSafetyCase_831() {
        assertTrue(PathSafetyValidator.isValid("safe_path_831/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_831/../traversal"));
    }
    @Test
    public void testPathSafetyCase_832() {
        assertTrue(PathSafetyValidator.isValid("safe_path_832/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_832/../traversal"));
    }
    @Test
    public void testPathSafetyCase_833() {
        assertTrue(PathSafetyValidator.isValid("safe_path_833/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_833/../traversal"));
    }
    @Test
    public void testPathSafetyCase_834() {
        assertTrue(PathSafetyValidator.isValid("safe_path_834/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_834/../traversal"));
    }
    @Test
    public void testPathSafetyCase_835() {
        assertTrue(PathSafetyValidator.isValid("safe_path_835/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_835/../traversal"));
    }
    @Test
    public void testPathSafetyCase_836() {
        assertTrue(PathSafetyValidator.isValid("safe_path_836/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_836/../traversal"));
    }
    @Test
    public void testPathSafetyCase_837() {
        assertTrue(PathSafetyValidator.isValid("safe_path_837/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_837/../traversal"));
    }
    @Test
    public void testPathSafetyCase_838() {
        assertTrue(PathSafetyValidator.isValid("safe_path_838/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_838/../traversal"));
    }
    @Test
    public void testPathSafetyCase_839() {
        assertTrue(PathSafetyValidator.isValid("safe_path_839/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_839/../traversal"));
    }
    @Test
    public void testPathSafetyCase_840() {
        assertTrue(PathSafetyValidator.isValid("safe_path_840/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_840/../traversal"));
    }
    @Test
    public void testPathSafetyCase_841() {
        assertTrue(PathSafetyValidator.isValid("safe_path_841/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_841/../traversal"));
    }
    @Test
    public void testPathSafetyCase_842() {
        assertTrue(PathSafetyValidator.isValid("safe_path_842/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_842/../traversal"));
    }
    @Test
    public void testPathSafetyCase_843() {
        assertTrue(PathSafetyValidator.isValid("safe_path_843/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_843/../traversal"));
    }
    @Test
    public void testPathSafetyCase_844() {
        assertTrue(PathSafetyValidator.isValid("safe_path_844/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_844/../traversal"));
    }
    @Test
    public void testPathSafetyCase_845() {
        assertTrue(PathSafetyValidator.isValid("safe_path_845/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_845/../traversal"));
    }
    @Test
    public void testPathSafetyCase_846() {
        assertTrue(PathSafetyValidator.isValid("safe_path_846/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_846/../traversal"));
    }
    @Test
    public void testPathSafetyCase_847() {
        assertTrue(PathSafetyValidator.isValid("safe_path_847/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_847/../traversal"));
    }
    @Test
    public void testPathSafetyCase_848() {
        assertTrue(PathSafetyValidator.isValid("safe_path_848/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_848/../traversal"));
    }
    @Test
    public void testPathSafetyCase_849() {
        assertTrue(PathSafetyValidator.isValid("safe_path_849/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_849/../traversal"));
    }
    @Test
    public void testPathSafetyCase_850() {
        assertTrue(PathSafetyValidator.isValid("safe_path_850/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_850/../traversal"));
    }
    @Test
    public void testPathSafetyCase_851() {
        assertTrue(PathSafetyValidator.isValid("safe_path_851/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_851/../traversal"));
    }
    @Test
    public void testPathSafetyCase_852() {
        assertTrue(PathSafetyValidator.isValid("safe_path_852/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_852/../traversal"));
    }
    @Test
    public void testPathSafetyCase_853() {
        assertTrue(PathSafetyValidator.isValid("safe_path_853/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_853/../traversal"));
    }
    @Test
    public void testPathSafetyCase_854() {
        assertTrue(PathSafetyValidator.isValid("safe_path_854/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_854/../traversal"));
    }
    @Test
    public void testPathSafetyCase_855() {
        assertTrue(PathSafetyValidator.isValid("safe_path_855/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_855/../traversal"));
    }
    @Test
    public void testPathSafetyCase_856() {
        assertTrue(PathSafetyValidator.isValid("safe_path_856/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_856/../traversal"));
    }
    @Test
    public void testPathSafetyCase_857() {
        assertTrue(PathSafetyValidator.isValid("safe_path_857/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_857/../traversal"));
    }
    @Test
    public void testPathSafetyCase_858() {
        assertTrue(PathSafetyValidator.isValid("safe_path_858/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_858/../traversal"));
    }
    @Test
    public void testPathSafetyCase_859() {
        assertTrue(PathSafetyValidator.isValid("safe_path_859/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_859/../traversal"));
    }
    @Test
    public void testPathSafetyCase_860() {
        assertTrue(PathSafetyValidator.isValid("safe_path_860/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_860/../traversal"));
    }
    @Test
    public void testPathSafetyCase_861() {
        assertTrue(PathSafetyValidator.isValid("safe_path_861/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_861/../traversal"));
    }
    @Test
    public void testPathSafetyCase_862() {
        assertTrue(PathSafetyValidator.isValid("safe_path_862/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_862/../traversal"));
    }
    @Test
    public void testPathSafetyCase_863() {
        assertTrue(PathSafetyValidator.isValid("safe_path_863/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_863/../traversal"));
    }
    @Test
    public void testPathSafetyCase_864() {
        assertTrue(PathSafetyValidator.isValid("safe_path_864/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_864/../traversal"));
    }
    @Test
    public void testPathSafetyCase_865() {
        assertTrue(PathSafetyValidator.isValid("safe_path_865/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_865/../traversal"));
    }
    @Test
    public void testPathSafetyCase_866() {
        assertTrue(PathSafetyValidator.isValid("safe_path_866/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_866/../traversal"));
    }
    @Test
    public void testPathSafetyCase_867() {
        assertTrue(PathSafetyValidator.isValid("safe_path_867/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_867/../traversal"));
    }
    @Test
    public void testPathSafetyCase_868() {
        assertTrue(PathSafetyValidator.isValid("safe_path_868/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_868/../traversal"));
    }
    @Test
    public void testPathSafetyCase_869() {
        assertTrue(PathSafetyValidator.isValid("safe_path_869/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_869/../traversal"));
    }
    @Test
    public void testPathSafetyCase_870() {
        assertTrue(PathSafetyValidator.isValid("safe_path_870/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_870/../traversal"));
    }
    @Test
    public void testPathSafetyCase_871() {
        assertTrue(PathSafetyValidator.isValid("safe_path_871/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_871/../traversal"));
    }
    @Test
    public void testPathSafetyCase_872() {
        assertTrue(PathSafetyValidator.isValid("safe_path_872/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_872/../traversal"));
    }
    @Test
    public void testPathSafetyCase_873() {
        assertTrue(PathSafetyValidator.isValid("safe_path_873/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_873/../traversal"));
    }
    @Test
    public void testPathSafetyCase_874() {
        assertTrue(PathSafetyValidator.isValid("safe_path_874/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_874/../traversal"));
    }
    @Test
    public void testPathSafetyCase_875() {
        assertTrue(PathSafetyValidator.isValid("safe_path_875/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_875/../traversal"));
    }
    @Test
    public void testPathSafetyCase_876() {
        assertTrue(PathSafetyValidator.isValid("safe_path_876/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_876/../traversal"));
    }
    @Test
    public void testPathSafetyCase_877() {
        assertTrue(PathSafetyValidator.isValid("safe_path_877/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_877/../traversal"));
    }
    @Test
    public void testPathSafetyCase_878() {
        assertTrue(PathSafetyValidator.isValid("safe_path_878/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_878/../traversal"));
    }
    @Test
    public void testPathSafetyCase_879() {
        assertTrue(PathSafetyValidator.isValid("safe_path_879/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_879/../traversal"));
    }
    @Test
    public void testPathSafetyCase_880() {
        assertTrue(PathSafetyValidator.isValid("safe_path_880/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_880/../traversal"));
    }
    @Test
    public void testPathSafetyCase_881() {
        assertTrue(PathSafetyValidator.isValid("safe_path_881/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_881/../traversal"));
    }
    @Test
    public void testPathSafetyCase_882() {
        assertTrue(PathSafetyValidator.isValid("safe_path_882/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_882/../traversal"));
    }
    @Test
    public void testPathSafetyCase_883() {
        assertTrue(PathSafetyValidator.isValid("safe_path_883/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_883/../traversal"));
    }
    @Test
    public void testPathSafetyCase_884() {
        assertTrue(PathSafetyValidator.isValid("safe_path_884/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_884/../traversal"));
    }
    @Test
    public void testPathSafetyCase_885() {
        assertTrue(PathSafetyValidator.isValid("safe_path_885/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_885/../traversal"));
    }
    @Test
    public void testPathSafetyCase_886() {
        assertTrue(PathSafetyValidator.isValid("safe_path_886/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_886/../traversal"));
    }
    @Test
    public void testPathSafetyCase_887() {
        assertTrue(PathSafetyValidator.isValid("safe_path_887/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_887/../traversal"));
    }
    @Test
    public void testPathSafetyCase_888() {
        assertTrue(PathSafetyValidator.isValid("safe_path_888/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_888/../traversal"));
    }
    @Test
    public void testPathSafetyCase_889() {
        assertTrue(PathSafetyValidator.isValid("safe_path_889/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_889/../traversal"));
    }
    @Test
    public void testPathSafetyCase_890() {
        assertTrue(PathSafetyValidator.isValid("safe_path_890/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_890/../traversal"));
    }
    @Test
    public void testPathSafetyCase_891() {
        assertTrue(PathSafetyValidator.isValid("safe_path_891/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_891/../traversal"));
    }
    @Test
    public void testPathSafetyCase_892() {
        assertTrue(PathSafetyValidator.isValid("safe_path_892/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_892/../traversal"));
    }
    @Test
    public void testPathSafetyCase_893() {
        assertTrue(PathSafetyValidator.isValid("safe_path_893/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_893/../traversal"));
    }
    @Test
    public void testPathSafetyCase_894() {
        assertTrue(PathSafetyValidator.isValid("safe_path_894/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_894/../traversal"));
    }
    @Test
    public void testPathSafetyCase_895() {
        assertTrue(PathSafetyValidator.isValid("safe_path_895/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_895/../traversal"));
    }
    @Test
    public void testPathSafetyCase_896() {
        assertTrue(PathSafetyValidator.isValid("safe_path_896/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_896/../traversal"));
    }
    @Test
    public void testPathSafetyCase_897() {
        assertTrue(PathSafetyValidator.isValid("safe_path_897/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_897/../traversal"));
    }
    @Test
    public void testPathSafetyCase_898() {
        assertTrue(PathSafetyValidator.isValid("safe_path_898/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_898/../traversal"));
    }
    @Test
    public void testPathSafetyCase_899() {
        assertTrue(PathSafetyValidator.isValid("safe_path_899/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_899/../traversal"));
    }
    @Test
    public void testPathSafetyCase_900() {
        assertTrue(PathSafetyValidator.isValid("safe_path_900/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_900/../traversal"));
    }
    @Test
    public void testPathSafetyCase_901() {
        assertTrue(PathSafetyValidator.isValid("safe_path_901/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_901/../traversal"));
    }
    @Test
    public void testPathSafetyCase_902() {
        assertTrue(PathSafetyValidator.isValid("safe_path_902/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_902/../traversal"));
    }
    @Test
    public void testPathSafetyCase_903() {
        assertTrue(PathSafetyValidator.isValid("safe_path_903/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_903/../traversal"));
    }
    @Test
    public void testPathSafetyCase_904() {
        assertTrue(PathSafetyValidator.isValid("safe_path_904/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_904/../traversal"));
    }
    @Test
    public void testPathSafetyCase_905() {
        assertTrue(PathSafetyValidator.isValid("safe_path_905/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_905/../traversal"));
    }
    @Test
    public void testPathSafetyCase_906() {
        assertTrue(PathSafetyValidator.isValid("safe_path_906/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_906/../traversal"));
    }
    @Test
    public void testPathSafetyCase_907() {
        assertTrue(PathSafetyValidator.isValid("safe_path_907/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_907/../traversal"));
    }
    @Test
    public void testPathSafetyCase_908() {
        assertTrue(PathSafetyValidator.isValid("safe_path_908/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_908/../traversal"));
    }
    @Test
    public void testPathSafetyCase_909() {
        assertTrue(PathSafetyValidator.isValid("safe_path_909/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_909/../traversal"));
    }
    @Test
    public void testPathSafetyCase_910() {
        assertTrue(PathSafetyValidator.isValid("safe_path_910/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_910/../traversal"));
    }
    @Test
    public void testPathSafetyCase_911() {
        assertTrue(PathSafetyValidator.isValid("safe_path_911/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_911/../traversal"));
    }
    @Test
    public void testPathSafetyCase_912() {
        assertTrue(PathSafetyValidator.isValid("safe_path_912/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_912/../traversal"));
    }
    @Test
    public void testPathSafetyCase_913() {
        assertTrue(PathSafetyValidator.isValid("safe_path_913/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_913/../traversal"));
    }
    @Test
    public void testPathSafetyCase_914() {
        assertTrue(PathSafetyValidator.isValid("safe_path_914/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_914/../traversal"));
    }
    @Test
    public void testPathSafetyCase_915() {
        assertTrue(PathSafetyValidator.isValid("safe_path_915/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_915/../traversal"));
    }
    @Test
    public void testPathSafetyCase_916() {
        assertTrue(PathSafetyValidator.isValid("safe_path_916/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_916/../traversal"));
    }
    @Test
    public void testPathSafetyCase_917() {
        assertTrue(PathSafetyValidator.isValid("safe_path_917/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_917/../traversal"));
    }
    @Test
    public void testPathSafetyCase_918() {
        assertTrue(PathSafetyValidator.isValid("safe_path_918/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_918/../traversal"));
    }
    @Test
    public void testPathSafetyCase_919() {
        assertTrue(PathSafetyValidator.isValid("safe_path_919/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_919/../traversal"));
    }
    @Test
    public void testPathSafetyCase_920() {
        assertTrue(PathSafetyValidator.isValid("safe_path_920/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_920/../traversal"));
    }
    @Test
    public void testPathSafetyCase_921() {
        assertTrue(PathSafetyValidator.isValid("safe_path_921/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_921/../traversal"));
    }
    @Test
    public void testPathSafetyCase_922() {
        assertTrue(PathSafetyValidator.isValid("safe_path_922/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_922/../traversal"));
    }
    @Test
    public void testPathSafetyCase_923() {
        assertTrue(PathSafetyValidator.isValid("safe_path_923/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_923/../traversal"));
    }
    @Test
    public void testPathSafetyCase_924() {
        assertTrue(PathSafetyValidator.isValid("safe_path_924/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_924/../traversal"));
    }
    @Test
    public void testPathSafetyCase_925() {
        assertTrue(PathSafetyValidator.isValid("safe_path_925/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_925/../traversal"));
    }
    @Test
    public void testPathSafetyCase_926() {
        assertTrue(PathSafetyValidator.isValid("safe_path_926/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_926/../traversal"));
    }
    @Test
    public void testPathSafetyCase_927() {
        assertTrue(PathSafetyValidator.isValid("safe_path_927/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_927/../traversal"));
    }
    @Test
    public void testPathSafetyCase_928() {
        assertTrue(PathSafetyValidator.isValid("safe_path_928/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_928/../traversal"));
    }
    @Test
    public void testPathSafetyCase_929() {
        assertTrue(PathSafetyValidator.isValid("safe_path_929/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_929/../traversal"));
    }
    @Test
    public void testPathSafetyCase_930() {
        assertTrue(PathSafetyValidator.isValid("safe_path_930/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_930/../traversal"));
    }
    @Test
    public void testPathSafetyCase_931() {
        assertTrue(PathSafetyValidator.isValid("safe_path_931/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_931/../traversal"));
    }
    @Test
    public void testPathSafetyCase_932() {
        assertTrue(PathSafetyValidator.isValid("safe_path_932/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_932/../traversal"));
    }
    @Test
    public void testPathSafetyCase_933() {
        assertTrue(PathSafetyValidator.isValid("safe_path_933/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_933/../traversal"));
    }
    @Test
    public void testPathSafetyCase_934() {
        assertTrue(PathSafetyValidator.isValid("safe_path_934/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_934/../traversal"));
    }
    @Test
    public void testPathSafetyCase_935() {
        assertTrue(PathSafetyValidator.isValid("safe_path_935/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_935/../traversal"));
    }
    @Test
    public void testPathSafetyCase_936() {
        assertTrue(PathSafetyValidator.isValid("safe_path_936/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_936/../traversal"));
    }
    @Test
    public void testPathSafetyCase_937() {
        assertTrue(PathSafetyValidator.isValid("safe_path_937/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_937/../traversal"));
    }
    @Test
    public void testPathSafetyCase_938() {
        assertTrue(PathSafetyValidator.isValid("safe_path_938/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_938/../traversal"));
    }
    @Test
    public void testPathSafetyCase_939() {
        assertTrue(PathSafetyValidator.isValid("safe_path_939/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_939/../traversal"));
    }
    @Test
    public void testPathSafetyCase_940() {
        assertTrue(PathSafetyValidator.isValid("safe_path_940/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_940/../traversal"));
    }
    @Test
    public void testPathSafetyCase_941() {
        assertTrue(PathSafetyValidator.isValid("safe_path_941/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_941/../traversal"));
    }
    @Test
    public void testPathSafetyCase_942() {
        assertTrue(PathSafetyValidator.isValid("safe_path_942/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_942/../traversal"));
    }
    @Test
    public void testPathSafetyCase_943() {
        assertTrue(PathSafetyValidator.isValid("safe_path_943/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_943/../traversal"));
    }
    @Test
    public void testPathSafetyCase_944() {
        assertTrue(PathSafetyValidator.isValid("safe_path_944/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_944/../traversal"));
    }
    @Test
    public void testPathSafetyCase_945() {
        assertTrue(PathSafetyValidator.isValid("safe_path_945/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_945/../traversal"));
    }
    @Test
    public void testPathSafetyCase_946() {
        assertTrue(PathSafetyValidator.isValid("safe_path_946/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_946/../traversal"));
    }
    @Test
    public void testPathSafetyCase_947() {
        assertTrue(PathSafetyValidator.isValid("safe_path_947/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_947/../traversal"));
    }
    @Test
    public void testPathSafetyCase_948() {
        assertTrue(PathSafetyValidator.isValid("safe_path_948/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_948/../traversal"));
    }
    @Test
    public void testPathSafetyCase_949() {
        assertTrue(PathSafetyValidator.isValid("safe_path_949/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_949/../traversal"));
    }
    @Test
    public void testPathSafetyCase_950() {
        assertTrue(PathSafetyValidator.isValid("safe_path_950/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_950/../traversal"));
    }
    @Test
    public void testPathSafetyCase_951() {
        assertTrue(PathSafetyValidator.isValid("safe_path_951/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_951/../traversal"));
    }
    @Test
    public void testPathSafetyCase_952() {
        assertTrue(PathSafetyValidator.isValid("safe_path_952/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_952/../traversal"));
    }
    @Test
    public void testPathSafetyCase_953() {
        assertTrue(PathSafetyValidator.isValid("safe_path_953/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_953/../traversal"));
    }
    @Test
    public void testPathSafetyCase_954() {
        assertTrue(PathSafetyValidator.isValid("safe_path_954/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_954/../traversal"));
    }
    @Test
    public void testPathSafetyCase_955() {
        assertTrue(PathSafetyValidator.isValid("safe_path_955/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_955/../traversal"));
    }
    @Test
    public void testPathSafetyCase_956() {
        assertTrue(PathSafetyValidator.isValid("safe_path_956/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_956/../traversal"));
    }
    @Test
    public void testPathSafetyCase_957() {
        assertTrue(PathSafetyValidator.isValid("safe_path_957/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_957/../traversal"));
    }
    @Test
    public void testPathSafetyCase_958() {
        assertTrue(PathSafetyValidator.isValid("safe_path_958/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_958/../traversal"));
    }
    @Test
    public void testPathSafetyCase_959() {
        assertTrue(PathSafetyValidator.isValid("safe_path_959/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_959/../traversal"));
    }
    @Test
    public void testPathSafetyCase_960() {
        assertTrue(PathSafetyValidator.isValid("safe_path_960/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_960/../traversal"));
    }
    @Test
    public void testPathSafetyCase_961() {
        assertTrue(PathSafetyValidator.isValid("safe_path_961/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_961/../traversal"));
    }
    @Test
    public void testPathSafetyCase_962() {
        assertTrue(PathSafetyValidator.isValid("safe_path_962/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_962/../traversal"));
    }
    @Test
    public void testPathSafetyCase_963() {
        assertTrue(PathSafetyValidator.isValid("safe_path_963/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_963/../traversal"));
    }
    @Test
    public void testPathSafetyCase_964() {
        assertTrue(PathSafetyValidator.isValid("safe_path_964/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_964/../traversal"));
    }
    @Test
    public void testPathSafetyCase_965() {
        assertTrue(PathSafetyValidator.isValid("safe_path_965/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_965/../traversal"));
    }
    @Test
    public void testPathSafetyCase_966() {
        assertTrue(PathSafetyValidator.isValid("safe_path_966/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_966/../traversal"));
    }
    @Test
    public void testPathSafetyCase_967() {
        assertTrue(PathSafetyValidator.isValid("safe_path_967/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_967/../traversal"));
    }
    @Test
    public void testPathSafetyCase_968() {
        assertTrue(PathSafetyValidator.isValid("safe_path_968/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_968/../traversal"));
    }
    @Test
    public void testPathSafetyCase_969() {
        assertTrue(PathSafetyValidator.isValid("safe_path_969/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_969/../traversal"));
    }
    @Test
    public void testPathSafetyCase_970() {
        assertTrue(PathSafetyValidator.isValid("safe_path_970/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_970/../traversal"));
    }
    @Test
    public void testPathSafetyCase_971() {
        assertTrue(PathSafetyValidator.isValid("safe_path_971/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_971/../traversal"));
    }
    @Test
    public void testPathSafetyCase_972() {
        assertTrue(PathSafetyValidator.isValid("safe_path_972/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_972/../traversal"));
    }
    @Test
    public void testPathSafetyCase_973() {
        assertTrue(PathSafetyValidator.isValid("safe_path_973/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_973/../traversal"));
    }
    @Test
    public void testPathSafetyCase_974() {
        assertTrue(PathSafetyValidator.isValid("safe_path_974/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_974/../traversal"));
    }
    @Test
    public void testPathSafetyCase_975() {
        assertTrue(PathSafetyValidator.isValid("safe_path_975/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_975/../traversal"));
    }
    @Test
    public void testPathSafetyCase_976() {
        assertTrue(PathSafetyValidator.isValid("safe_path_976/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_976/../traversal"));
    }
    @Test
    public void testPathSafetyCase_977() {
        assertTrue(PathSafetyValidator.isValid("safe_path_977/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_977/../traversal"));
    }
    @Test
    public void testPathSafetyCase_978() {
        assertTrue(PathSafetyValidator.isValid("safe_path_978/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_978/../traversal"));
    }
    @Test
    public void testPathSafetyCase_979() {
        assertTrue(PathSafetyValidator.isValid("safe_path_979/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_979/../traversal"));
    }
    @Test
    public void testPathSafetyCase_980() {
        assertTrue(PathSafetyValidator.isValid("safe_path_980/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_980/../traversal"));
    }
    @Test
    public void testPathSafetyCase_981() {
        assertTrue(PathSafetyValidator.isValid("safe_path_981/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_981/../traversal"));
    }
    @Test
    public void testPathSafetyCase_982() {
        assertTrue(PathSafetyValidator.isValid("safe_path_982/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_982/../traversal"));
    }
    @Test
    public void testPathSafetyCase_983() {
        assertTrue(PathSafetyValidator.isValid("safe_path_983/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_983/../traversal"));
    }
    @Test
    public void testPathSafetyCase_984() {
        assertTrue(PathSafetyValidator.isValid("safe_path_984/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_984/../traversal"));
    }
    @Test
    public void testPathSafetyCase_985() {
        assertTrue(PathSafetyValidator.isValid("safe_path_985/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_985/../traversal"));
    }
    @Test
    public void testPathSafetyCase_986() {
        assertTrue(PathSafetyValidator.isValid("safe_path_986/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_986/../traversal"));
    }
    @Test
    public void testPathSafetyCase_987() {
        assertTrue(PathSafetyValidator.isValid("safe_path_987/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_987/../traversal"));
    }
    @Test
    public void testPathSafetyCase_988() {
        assertTrue(PathSafetyValidator.isValid("safe_path_988/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_988/../traversal"));
    }
    @Test
    public void testPathSafetyCase_989() {
        assertTrue(PathSafetyValidator.isValid("safe_path_989/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_989/../traversal"));
    }
    @Test
    public void testPathSafetyCase_990() {
        assertTrue(PathSafetyValidator.isValid("safe_path_990/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_990/../traversal"));
    }
    @Test
    public void testPathSafetyCase_991() {
        assertTrue(PathSafetyValidator.isValid("safe_path_991/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_991/../traversal"));
    }
    @Test
    public void testPathSafetyCase_992() {
        assertTrue(PathSafetyValidator.isValid("safe_path_992/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_992/../traversal"));
    }
    @Test
    public void testPathSafetyCase_993() {
        assertTrue(PathSafetyValidator.isValid("safe_path_993/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_993/../traversal"));
    }
    @Test
    public void testPathSafetyCase_994() {
        assertTrue(PathSafetyValidator.isValid("safe_path_994/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_994/../traversal"));
    }
    @Test
    public void testPathSafetyCase_995() {
        assertTrue(PathSafetyValidator.isValid("safe_path_995/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_995/../traversal"));
    }
    @Test
    public void testPathSafetyCase_996() {
        assertTrue(PathSafetyValidator.isValid("safe_path_996/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_996/../traversal"));
    }
    @Test
    public void testPathSafetyCase_997() {
        assertTrue(PathSafetyValidator.isValid("safe_path_997/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_997/../traversal"));
    }
    @Test
    public void testPathSafetyCase_998() {
        assertTrue(PathSafetyValidator.isValid("safe_path_998/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_998/../traversal"));
    }
    @Test
    public void testPathSafetyCase_999() {
        assertTrue(PathSafetyValidator.isValid("safe_path_999/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_999/../traversal"));
    }
    @Test
    public void testPathSafetyCase_1000() {
        assertTrue(PathSafetyValidator.isValid("safe_path_1000/subdir"));
        assertFalse(PathSafetyValidator.isValid("unsafe_path_1000/../traversal"));
    }
}

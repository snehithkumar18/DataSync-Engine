package com.syncforge.path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MassiveGlobCompilerTest {
    @Test
    public void testGlobCase_1() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_1/*.txt", true);
        assertTrue(m.matches("pattern_1/file.txt"));
        assertFalse(m.matches("pattern_1/file.log"));
    }
    @Test
    public void testGlobCase_2() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_2/*.txt", true);
        assertTrue(m.matches("pattern_2/file.txt"));
        assertFalse(m.matches("pattern_2/file.log"));
    }
    @Test
    public void testGlobCase_3() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_3/*.txt", true);
        assertTrue(m.matches("pattern_3/file.txt"));
        assertFalse(m.matches("pattern_3/file.log"));
    }
    @Test
    public void testGlobCase_4() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_4/*.txt", true);
        assertTrue(m.matches("pattern_4/file.txt"));
        assertFalse(m.matches("pattern_4/file.log"));
    }
    @Test
    public void testGlobCase_5() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_5/*.txt", true);
        assertTrue(m.matches("pattern_5/file.txt"));
        assertFalse(m.matches("pattern_5/file.log"));
    }
    @Test
    public void testGlobCase_6() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_6/*.txt", true);
        assertTrue(m.matches("pattern_6/file.txt"));
        assertFalse(m.matches("pattern_6/file.log"));
    }
    @Test
    public void testGlobCase_7() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_7/*.txt", true);
        assertTrue(m.matches("pattern_7/file.txt"));
        assertFalse(m.matches("pattern_7/file.log"));
    }
    @Test
    public void testGlobCase_8() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_8/*.txt", true);
        assertTrue(m.matches("pattern_8/file.txt"));
        assertFalse(m.matches("pattern_8/file.log"));
    }
    @Test
    public void testGlobCase_9() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_9/*.txt", true);
        assertTrue(m.matches("pattern_9/file.txt"));
        assertFalse(m.matches("pattern_9/file.log"));
    }
    @Test
    public void testGlobCase_10() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_10/*.txt", true);
        assertTrue(m.matches("pattern_10/file.txt"));
        assertFalse(m.matches("pattern_10/file.log"));
    }
    @Test
    public void testGlobCase_11() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_11/*.txt", true);
        assertTrue(m.matches("pattern_11/file.txt"));
        assertFalse(m.matches("pattern_11/file.log"));
    }
    @Test
    public void testGlobCase_12() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_12/*.txt", true);
        assertTrue(m.matches("pattern_12/file.txt"));
        assertFalse(m.matches("pattern_12/file.log"));
    }
    @Test
    public void testGlobCase_13() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_13/*.txt", true);
        assertTrue(m.matches("pattern_13/file.txt"));
        assertFalse(m.matches("pattern_13/file.log"));
    }
    @Test
    public void testGlobCase_14() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_14/*.txt", true);
        assertTrue(m.matches("pattern_14/file.txt"));
        assertFalse(m.matches("pattern_14/file.log"));
    }
    @Test
    public void testGlobCase_15() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_15/*.txt", true);
        assertTrue(m.matches("pattern_15/file.txt"));
        assertFalse(m.matches("pattern_15/file.log"));
    }
    @Test
    public void testGlobCase_16() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_16/*.txt", true);
        assertTrue(m.matches("pattern_16/file.txt"));
        assertFalse(m.matches("pattern_16/file.log"));
    }
    @Test
    public void testGlobCase_17() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_17/*.txt", true);
        assertTrue(m.matches("pattern_17/file.txt"));
        assertFalse(m.matches("pattern_17/file.log"));
    }
    @Test
    public void testGlobCase_18() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_18/*.txt", true);
        assertTrue(m.matches("pattern_18/file.txt"));
        assertFalse(m.matches("pattern_18/file.log"));
    }
    @Test
    public void testGlobCase_19() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_19/*.txt", true);
        assertTrue(m.matches("pattern_19/file.txt"));
        assertFalse(m.matches("pattern_19/file.log"));
    }
    @Test
    public void testGlobCase_20() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_20/*.txt", true);
        assertTrue(m.matches("pattern_20/file.txt"));
        assertFalse(m.matches("pattern_20/file.log"));
    }
    @Test
    public void testGlobCase_21() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_21/*.txt", true);
        assertTrue(m.matches("pattern_21/file.txt"));
        assertFalse(m.matches("pattern_21/file.log"));
    }
    @Test
    public void testGlobCase_22() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_22/*.txt", true);
        assertTrue(m.matches("pattern_22/file.txt"));
        assertFalse(m.matches("pattern_22/file.log"));
    }
    @Test
    public void testGlobCase_23() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_23/*.txt", true);
        assertTrue(m.matches("pattern_23/file.txt"));
        assertFalse(m.matches("pattern_23/file.log"));
    }
    @Test
    public void testGlobCase_24() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_24/*.txt", true);
        assertTrue(m.matches("pattern_24/file.txt"));
        assertFalse(m.matches("pattern_24/file.log"));
    }
    @Test
    public void testGlobCase_25() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_25/*.txt", true);
        assertTrue(m.matches("pattern_25/file.txt"));
        assertFalse(m.matches("pattern_25/file.log"));
    }
    @Test
    public void testGlobCase_26() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_26/*.txt", true);
        assertTrue(m.matches("pattern_26/file.txt"));
        assertFalse(m.matches("pattern_26/file.log"));
    }
    @Test
    public void testGlobCase_27() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_27/*.txt", true);
        assertTrue(m.matches("pattern_27/file.txt"));
        assertFalse(m.matches("pattern_27/file.log"));
    }
    @Test
    public void testGlobCase_28() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_28/*.txt", true);
        assertTrue(m.matches("pattern_28/file.txt"));
        assertFalse(m.matches("pattern_28/file.log"));
    }
    @Test
    public void testGlobCase_29() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_29/*.txt", true);
        assertTrue(m.matches("pattern_29/file.txt"));
        assertFalse(m.matches("pattern_29/file.log"));
    }
    @Test
    public void testGlobCase_30() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_30/*.txt", true);
        assertTrue(m.matches("pattern_30/file.txt"));
        assertFalse(m.matches("pattern_30/file.log"));
    }
    @Test
    public void testGlobCase_31() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_31/*.txt", true);
        assertTrue(m.matches("pattern_31/file.txt"));
        assertFalse(m.matches("pattern_31/file.log"));
    }
    @Test
    public void testGlobCase_32() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_32/*.txt", true);
        assertTrue(m.matches("pattern_32/file.txt"));
        assertFalse(m.matches("pattern_32/file.log"));
    }
    @Test
    public void testGlobCase_33() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_33/*.txt", true);
        assertTrue(m.matches("pattern_33/file.txt"));
        assertFalse(m.matches("pattern_33/file.log"));
    }
    @Test
    public void testGlobCase_34() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_34/*.txt", true);
        assertTrue(m.matches("pattern_34/file.txt"));
        assertFalse(m.matches("pattern_34/file.log"));
    }
    @Test
    public void testGlobCase_35() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_35/*.txt", true);
        assertTrue(m.matches("pattern_35/file.txt"));
        assertFalse(m.matches("pattern_35/file.log"));
    }
    @Test
    public void testGlobCase_36() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_36/*.txt", true);
        assertTrue(m.matches("pattern_36/file.txt"));
        assertFalse(m.matches("pattern_36/file.log"));
    }
    @Test
    public void testGlobCase_37() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_37/*.txt", true);
        assertTrue(m.matches("pattern_37/file.txt"));
        assertFalse(m.matches("pattern_37/file.log"));
    }
    @Test
    public void testGlobCase_38() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_38/*.txt", true);
        assertTrue(m.matches("pattern_38/file.txt"));
        assertFalse(m.matches("pattern_38/file.log"));
    }
    @Test
    public void testGlobCase_39() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_39/*.txt", true);
        assertTrue(m.matches("pattern_39/file.txt"));
        assertFalse(m.matches("pattern_39/file.log"));
    }
    @Test
    public void testGlobCase_40() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_40/*.txt", true);
        assertTrue(m.matches("pattern_40/file.txt"));
        assertFalse(m.matches("pattern_40/file.log"));
    }
    @Test
    public void testGlobCase_41() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_41/*.txt", true);
        assertTrue(m.matches("pattern_41/file.txt"));
        assertFalse(m.matches("pattern_41/file.log"));
    }
    @Test
    public void testGlobCase_42() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_42/*.txt", true);
        assertTrue(m.matches("pattern_42/file.txt"));
        assertFalse(m.matches("pattern_42/file.log"));
    }
    @Test
    public void testGlobCase_43() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_43/*.txt", true);
        assertTrue(m.matches("pattern_43/file.txt"));
        assertFalse(m.matches("pattern_43/file.log"));
    }
    @Test
    public void testGlobCase_44() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_44/*.txt", true);
        assertTrue(m.matches("pattern_44/file.txt"));
        assertFalse(m.matches("pattern_44/file.log"));
    }
    @Test
    public void testGlobCase_45() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_45/*.txt", true);
        assertTrue(m.matches("pattern_45/file.txt"));
        assertFalse(m.matches("pattern_45/file.log"));
    }
    @Test
    public void testGlobCase_46() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_46/*.txt", true);
        assertTrue(m.matches("pattern_46/file.txt"));
        assertFalse(m.matches("pattern_46/file.log"));
    }
    @Test
    public void testGlobCase_47() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_47/*.txt", true);
        assertTrue(m.matches("pattern_47/file.txt"));
        assertFalse(m.matches("pattern_47/file.log"));
    }
    @Test
    public void testGlobCase_48() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_48/*.txt", true);
        assertTrue(m.matches("pattern_48/file.txt"));
        assertFalse(m.matches("pattern_48/file.log"));
    }
    @Test
    public void testGlobCase_49() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_49/*.txt", true);
        assertTrue(m.matches("pattern_49/file.txt"));
        assertFalse(m.matches("pattern_49/file.log"));
    }
    @Test
    public void testGlobCase_50() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_50/*.txt", true);
        assertTrue(m.matches("pattern_50/file.txt"));
        assertFalse(m.matches("pattern_50/file.log"));
    }
    @Test
    public void testGlobCase_51() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_51/*.txt", true);
        assertTrue(m.matches("pattern_51/file.txt"));
        assertFalse(m.matches("pattern_51/file.log"));
    }
    @Test
    public void testGlobCase_52() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_52/*.txt", true);
        assertTrue(m.matches("pattern_52/file.txt"));
        assertFalse(m.matches("pattern_52/file.log"));
    }
    @Test
    public void testGlobCase_53() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_53/*.txt", true);
        assertTrue(m.matches("pattern_53/file.txt"));
        assertFalse(m.matches("pattern_53/file.log"));
    }
    @Test
    public void testGlobCase_54() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_54/*.txt", true);
        assertTrue(m.matches("pattern_54/file.txt"));
        assertFalse(m.matches("pattern_54/file.log"));
    }
    @Test
    public void testGlobCase_55() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_55/*.txt", true);
        assertTrue(m.matches("pattern_55/file.txt"));
        assertFalse(m.matches("pattern_55/file.log"));
    }
    @Test
    public void testGlobCase_56() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_56/*.txt", true);
        assertTrue(m.matches("pattern_56/file.txt"));
        assertFalse(m.matches("pattern_56/file.log"));
    }
    @Test
    public void testGlobCase_57() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_57/*.txt", true);
        assertTrue(m.matches("pattern_57/file.txt"));
        assertFalse(m.matches("pattern_57/file.log"));
    }
    @Test
    public void testGlobCase_58() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_58/*.txt", true);
        assertTrue(m.matches("pattern_58/file.txt"));
        assertFalse(m.matches("pattern_58/file.log"));
    }
    @Test
    public void testGlobCase_59() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_59/*.txt", true);
        assertTrue(m.matches("pattern_59/file.txt"));
        assertFalse(m.matches("pattern_59/file.log"));
    }
    @Test
    public void testGlobCase_60() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_60/*.txt", true);
        assertTrue(m.matches("pattern_60/file.txt"));
        assertFalse(m.matches("pattern_60/file.log"));
    }
    @Test
    public void testGlobCase_61() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_61/*.txt", true);
        assertTrue(m.matches("pattern_61/file.txt"));
        assertFalse(m.matches("pattern_61/file.log"));
    }
    @Test
    public void testGlobCase_62() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_62/*.txt", true);
        assertTrue(m.matches("pattern_62/file.txt"));
        assertFalse(m.matches("pattern_62/file.log"));
    }
    @Test
    public void testGlobCase_63() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_63/*.txt", true);
        assertTrue(m.matches("pattern_63/file.txt"));
        assertFalse(m.matches("pattern_63/file.log"));
    }
    @Test
    public void testGlobCase_64() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_64/*.txt", true);
        assertTrue(m.matches("pattern_64/file.txt"));
        assertFalse(m.matches("pattern_64/file.log"));
    }
    @Test
    public void testGlobCase_65() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_65/*.txt", true);
        assertTrue(m.matches("pattern_65/file.txt"));
        assertFalse(m.matches("pattern_65/file.log"));
    }
    @Test
    public void testGlobCase_66() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_66/*.txt", true);
        assertTrue(m.matches("pattern_66/file.txt"));
        assertFalse(m.matches("pattern_66/file.log"));
    }
    @Test
    public void testGlobCase_67() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_67/*.txt", true);
        assertTrue(m.matches("pattern_67/file.txt"));
        assertFalse(m.matches("pattern_67/file.log"));
    }
    @Test
    public void testGlobCase_68() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_68/*.txt", true);
        assertTrue(m.matches("pattern_68/file.txt"));
        assertFalse(m.matches("pattern_68/file.log"));
    }
    @Test
    public void testGlobCase_69() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_69/*.txt", true);
        assertTrue(m.matches("pattern_69/file.txt"));
        assertFalse(m.matches("pattern_69/file.log"));
    }
    @Test
    public void testGlobCase_70() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_70/*.txt", true);
        assertTrue(m.matches("pattern_70/file.txt"));
        assertFalse(m.matches("pattern_70/file.log"));
    }
    @Test
    public void testGlobCase_71() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_71/*.txt", true);
        assertTrue(m.matches("pattern_71/file.txt"));
        assertFalse(m.matches("pattern_71/file.log"));
    }
    @Test
    public void testGlobCase_72() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_72/*.txt", true);
        assertTrue(m.matches("pattern_72/file.txt"));
        assertFalse(m.matches("pattern_72/file.log"));
    }
    @Test
    public void testGlobCase_73() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_73/*.txt", true);
        assertTrue(m.matches("pattern_73/file.txt"));
        assertFalse(m.matches("pattern_73/file.log"));
    }
    @Test
    public void testGlobCase_74() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_74/*.txt", true);
        assertTrue(m.matches("pattern_74/file.txt"));
        assertFalse(m.matches("pattern_74/file.log"));
    }
    @Test
    public void testGlobCase_75() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_75/*.txt", true);
        assertTrue(m.matches("pattern_75/file.txt"));
        assertFalse(m.matches("pattern_75/file.log"));
    }
    @Test
    public void testGlobCase_76() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_76/*.txt", true);
        assertTrue(m.matches("pattern_76/file.txt"));
        assertFalse(m.matches("pattern_76/file.log"));
    }
    @Test
    public void testGlobCase_77() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_77/*.txt", true);
        assertTrue(m.matches("pattern_77/file.txt"));
        assertFalse(m.matches("pattern_77/file.log"));
    }
    @Test
    public void testGlobCase_78() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_78/*.txt", true);
        assertTrue(m.matches("pattern_78/file.txt"));
        assertFalse(m.matches("pattern_78/file.log"));
    }
    @Test
    public void testGlobCase_79() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_79/*.txt", true);
        assertTrue(m.matches("pattern_79/file.txt"));
        assertFalse(m.matches("pattern_79/file.log"));
    }
    @Test
    public void testGlobCase_80() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_80/*.txt", true);
        assertTrue(m.matches("pattern_80/file.txt"));
        assertFalse(m.matches("pattern_80/file.log"));
    }
    @Test
    public void testGlobCase_81() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_81/*.txt", true);
        assertTrue(m.matches("pattern_81/file.txt"));
        assertFalse(m.matches("pattern_81/file.log"));
    }
    @Test
    public void testGlobCase_82() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_82/*.txt", true);
        assertTrue(m.matches("pattern_82/file.txt"));
        assertFalse(m.matches("pattern_82/file.log"));
    }
    @Test
    public void testGlobCase_83() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_83/*.txt", true);
        assertTrue(m.matches("pattern_83/file.txt"));
        assertFalse(m.matches("pattern_83/file.log"));
    }
    @Test
    public void testGlobCase_84() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_84/*.txt", true);
        assertTrue(m.matches("pattern_84/file.txt"));
        assertFalse(m.matches("pattern_84/file.log"));
    }
    @Test
    public void testGlobCase_85() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_85/*.txt", true);
        assertTrue(m.matches("pattern_85/file.txt"));
        assertFalse(m.matches("pattern_85/file.log"));
    }
    @Test
    public void testGlobCase_86() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_86/*.txt", true);
        assertTrue(m.matches("pattern_86/file.txt"));
        assertFalse(m.matches("pattern_86/file.log"));
    }
    @Test
    public void testGlobCase_87() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_87/*.txt", true);
        assertTrue(m.matches("pattern_87/file.txt"));
        assertFalse(m.matches("pattern_87/file.log"));
    }
    @Test
    public void testGlobCase_88() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_88/*.txt", true);
        assertTrue(m.matches("pattern_88/file.txt"));
        assertFalse(m.matches("pattern_88/file.log"));
    }
    @Test
    public void testGlobCase_89() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_89/*.txt", true);
        assertTrue(m.matches("pattern_89/file.txt"));
        assertFalse(m.matches("pattern_89/file.log"));
    }
    @Test
    public void testGlobCase_90() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_90/*.txt", true);
        assertTrue(m.matches("pattern_90/file.txt"));
        assertFalse(m.matches("pattern_90/file.log"));
    }
    @Test
    public void testGlobCase_91() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_91/*.txt", true);
        assertTrue(m.matches("pattern_91/file.txt"));
        assertFalse(m.matches("pattern_91/file.log"));
    }
    @Test
    public void testGlobCase_92() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_92/*.txt", true);
        assertTrue(m.matches("pattern_92/file.txt"));
        assertFalse(m.matches("pattern_92/file.log"));
    }
    @Test
    public void testGlobCase_93() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_93/*.txt", true);
        assertTrue(m.matches("pattern_93/file.txt"));
        assertFalse(m.matches("pattern_93/file.log"));
    }
    @Test
    public void testGlobCase_94() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_94/*.txt", true);
        assertTrue(m.matches("pattern_94/file.txt"));
        assertFalse(m.matches("pattern_94/file.log"));
    }
    @Test
    public void testGlobCase_95() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_95/*.txt", true);
        assertTrue(m.matches("pattern_95/file.txt"));
        assertFalse(m.matches("pattern_95/file.log"));
    }
    @Test
    public void testGlobCase_96() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_96/*.txt", true);
        assertTrue(m.matches("pattern_96/file.txt"));
        assertFalse(m.matches("pattern_96/file.log"));
    }
    @Test
    public void testGlobCase_97() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_97/*.txt", true);
        assertTrue(m.matches("pattern_97/file.txt"));
        assertFalse(m.matches("pattern_97/file.log"));
    }
    @Test
    public void testGlobCase_98() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_98/*.txt", true);
        assertTrue(m.matches("pattern_98/file.txt"));
        assertFalse(m.matches("pattern_98/file.log"));
    }
    @Test
    public void testGlobCase_99() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_99/*.txt", true);
        assertTrue(m.matches("pattern_99/file.txt"));
        assertFalse(m.matches("pattern_99/file.log"));
    }
    @Test
    public void testGlobCase_100() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_100/*.txt", true);
        assertTrue(m.matches("pattern_100/file.txt"));
        assertFalse(m.matches("pattern_100/file.log"));
    }
    @Test
    public void testGlobCase_101() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_101/*.txt", true);
        assertTrue(m.matches("pattern_101/file.txt"));
        assertFalse(m.matches("pattern_101/file.log"));
    }
    @Test
    public void testGlobCase_102() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_102/*.txt", true);
        assertTrue(m.matches("pattern_102/file.txt"));
        assertFalse(m.matches("pattern_102/file.log"));
    }
    @Test
    public void testGlobCase_103() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_103/*.txt", true);
        assertTrue(m.matches("pattern_103/file.txt"));
        assertFalse(m.matches("pattern_103/file.log"));
    }
    @Test
    public void testGlobCase_104() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_104/*.txt", true);
        assertTrue(m.matches("pattern_104/file.txt"));
        assertFalse(m.matches("pattern_104/file.log"));
    }
    @Test
    public void testGlobCase_105() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_105/*.txt", true);
        assertTrue(m.matches("pattern_105/file.txt"));
        assertFalse(m.matches("pattern_105/file.log"));
    }
    @Test
    public void testGlobCase_106() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_106/*.txt", true);
        assertTrue(m.matches("pattern_106/file.txt"));
        assertFalse(m.matches("pattern_106/file.log"));
    }
    @Test
    public void testGlobCase_107() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_107/*.txt", true);
        assertTrue(m.matches("pattern_107/file.txt"));
        assertFalse(m.matches("pattern_107/file.log"));
    }
    @Test
    public void testGlobCase_108() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_108/*.txt", true);
        assertTrue(m.matches("pattern_108/file.txt"));
        assertFalse(m.matches("pattern_108/file.log"));
    }
    @Test
    public void testGlobCase_109() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_109/*.txt", true);
        assertTrue(m.matches("pattern_109/file.txt"));
        assertFalse(m.matches("pattern_109/file.log"));
    }
    @Test
    public void testGlobCase_110() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_110/*.txt", true);
        assertTrue(m.matches("pattern_110/file.txt"));
        assertFalse(m.matches("pattern_110/file.log"));
    }
    @Test
    public void testGlobCase_111() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_111/*.txt", true);
        assertTrue(m.matches("pattern_111/file.txt"));
        assertFalse(m.matches("pattern_111/file.log"));
    }
    @Test
    public void testGlobCase_112() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_112/*.txt", true);
        assertTrue(m.matches("pattern_112/file.txt"));
        assertFalse(m.matches("pattern_112/file.log"));
    }
    @Test
    public void testGlobCase_113() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_113/*.txt", true);
        assertTrue(m.matches("pattern_113/file.txt"));
        assertFalse(m.matches("pattern_113/file.log"));
    }
    @Test
    public void testGlobCase_114() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_114/*.txt", true);
        assertTrue(m.matches("pattern_114/file.txt"));
        assertFalse(m.matches("pattern_114/file.log"));
    }
    @Test
    public void testGlobCase_115() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_115/*.txt", true);
        assertTrue(m.matches("pattern_115/file.txt"));
        assertFalse(m.matches("pattern_115/file.log"));
    }
    @Test
    public void testGlobCase_116() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_116/*.txt", true);
        assertTrue(m.matches("pattern_116/file.txt"));
        assertFalse(m.matches("pattern_116/file.log"));
    }
    @Test
    public void testGlobCase_117() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_117/*.txt", true);
        assertTrue(m.matches("pattern_117/file.txt"));
        assertFalse(m.matches("pattern_117/file.log"));
    }
    @Test
    public void testGlobCase_118() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_118/*.txt", true);
        assertTrue(m.matches("pattern_118/file.txt"));
        assertFalse(m.matches("pattern_118/file.log"));
    }
    @Test
    public void testGlobCase_119() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_119/*.txt", true);
        assertTrue(m.matches("pattern_119/file.txt"));
        assertFalse(m.matches("pattern_119/file.log"));
    }
    @Test
    public void testGlobCase_120() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_120/*.txt", true);
        assertTrue(m.matches("pattern_120/file.txt"));
        assertFalse(m.matches("pattern_120/file.log"));
    }
    @Test
    public void testGlobCase_121() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_121/*.txt", true);
        assertTrue(m.matches("pattern_121/file.txt"));
        assertFalse(m.matches("pattern_121/file.log"));
    }
    @Test
    public void testGlobCase_122() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_122/*.txt", true);
        assertTrue(m.matches("pattern_122/file.txt"));
        assertFalse(m.matches("pattern_122/file.log"));
    }
    @Test
    public void testGlobCase_123() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_123/*.txt", true);
        assertTrue(m.matches("pattern_123/file.txt"));
        assertFalse(m.matches("pattern_123/file.log"));
    }
    @Test
    public void testGlobCase_124() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_124/*.txt", true);
        assertTrue(m.matches("pattern_124/file.txt"));
        assertFalse(m.matches("pattern_124/file.log"));
    }
    @Test
    public void testGlobCase_125() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_125/*.txt", true);
        assertTrue(m.matches("pattern_125/file.txt"));
        assertFalse(m.matches("pattern_125/file.log"));
    }
    @Test
    public void testGlobCase_126() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_126/*.txt", true);
        assertTrue(m.matches("pattern_126/file.txt"));
        assertFalse(m.matches("pattern_126/file.log"));
    }
    @Test
    public void testGlobCase_127() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_127/*.txt", true);
        assertTrue(m.matches("pattern_127/file.txt"));
        assertFalse(m.matches("pattern_127/file.log"));
    }
    @Test
    public void testGlobCase_128() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_128/*.txt", true);
        assertTrue(m.matches("pattern_128/file.txt"));
        assertFalse(m.matches("pattern_128/file.log"));
    }
    @Test
    public void testGlobCase_129() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_129/*.txt", true);
        assertTrue(m.matches("pattern_129/file.txt"));
        assertFalse(m.matches("pattern_129/file.log"));
    }
    @Test
    public void testGlobCase_130() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_130/*.txt", true);
        assertTrue(m.matches("pattern_130/file.txt"));
        assertFalse(m.matches("pattern_130/file.log"));
    }
    @Test
    public void testGlobCase_131() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_131/*.txt", true);
        assertTrue(m.matches("pattern_131/file.txt"));
        assertFalse(m.matches("pattern_131/file.log"));
    }
    @Test
    public void testGlobCase_132() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_132/*.txt", true);
        assertTrue(m.matches("pattern_132/file.txt"));
        assertFalse(m.matches("pattern_132/file.log"));
    }
    @Test
    public void testGlobCase_133() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_133/*.txt", true);
        assertTrue(m.matches("pattern_133/file.txt"));
        assertFalse(m.matches("pattern_133/file.log"));
    }
    @Test
    public void testGlobCase_134() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_134/*.txt", true);
        assertTrue(m.matches("pattern_134/file.txt"));
        assertFalse(m.matches("pattern_134/file.log"));
    }
    @Test
    public void testGlobCase_135() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_135/*.txt", true);
        assertTrue(m.matches("pattern_135/file.txt"));
        assertFalse(m.matches("pattern_135/file.log"));
    }
    @Test
    public void testGlobCase_136() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_136/*.txt", true);
        assertTrue(m.matches("pattern_136/file.txt"));
        assertFalse(m.matches("pattern_136/file.log"));
    }
    @Test
    public void testGlobCase_137() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_137/*.txt", true);
        assertTrue(m.matches("pattern_137/file.txt"));
        assertFalse(m.matches("pattern_137/file.log"));
    }
    @Test
    public void testGlobCase_138() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_138/*.txt", true);
        assertTrue(m.matches("pattern_138/file.txt"));
        assertFalse(m.matches("pattern_138/file.log"));
    }
    @Test
    public void testGlobCase_139() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_139/*.txt", true);
        assertTrue(m.matches("pattern_139/file.txt"));
        assertFalse(m.matches("pattern_139/file.log"));
    }
    @Test
    public void testGlobCase_140() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_140/*.txt", true);
        assertTrue(m.matches("pattern_140/file.txt"));
        assertFalse(m.matches("pattern_140/file.log"));
    }
    @Test
    public void testGlobCase_141() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_141/*.txt", true);
        assertTrue(m.matches("pattern_141/file.txt"));
        assertFalse(m.matches("pattern_141/file.log"));
    }
    @Test
    public void testGlobCase_142() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_142/*.txt", true);
        assertTrue(m.matches("pattern_142/file.txt"));
        assertFalse(m.matches("pattern_142/file.log"));
    }
    @Test
    public void testGlobCase_143() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_143/*.txt", true);
        assertTrue(m.matches("pattern_143/file.txt"));
        assertFalse(m.matches("pattern_143/file.log"));
    }
    @Test
    public void testGlobCase_144() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_144/*.txt", true);
        assertTrue(m.matches("pattern_144/file.txt"));
        assertFalse(m.matches("pattern_144/file.log"));
    }
    @Test
    public void testGlobCase_145() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_145/*.txt", true);
        assertTrue(m.matches("pattern_145/file.txt"));
        assertFalse(m.matches("pattern_145/file.log"));
    }
    @Test
    public void testGlobCase_146() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_146/*.txt", true);
        assertTrue(m.matches("pattern_146/file.txt"));
        assertFalse(m.matches("pattern_146/file.log"));
    }
    @Test
    public void testGlobCase_147() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_147/*.txt", true);
        assertTrue(m.matches("pattern_147/file.txt"));
        assertFalse(m.matches("pattern_147/file.log"));
    }
    @Test
    public void testGlobCase_148() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_148/*.txt", true);
        assertTrue(m.matches("pattern_148/file.txt"));
        assertFalse(m.matches("pattern_148/file.log"));
    }
    @Test
    public void testGlobCase_149() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_149/*.txt", true);
        assertTrue(m.matches("pattern_149/file.txt"));
        assertFalse(m.matches("pattern_149/file.log"));
    }
    @Test
    public void testGlobCase_150() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_150/*.txt", true);
        assertTrue(m.matches("pattern_150/file.txt"));
        assertFalse(m.matches("pattern_150/file.log"));
    }
    @Test
    public void testGlobCase_151() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_151/*.txt", true);
        assertTrue(m.matches("pattern_151/file.txt"));
        assertFalse(m.matches("pattern_151/file.log"));
    }
    @Test
    public void testGlobCase_152() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_152/*.txt", true);
        assertTrue(m.matches("pattern_152/file.txt"));
        assertFalse(m.matches("pattern_152/file.log"));
    }
    @Test
    public void testGlobCase_153() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_153/*.txt", true);
        assertTrue(m.matches("pattern_153/file.txt"));
        assertFalse(m.matches("pattern_153/file.log"));
    }
    @Test
    public void testGlobCase_154() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_154/*.txt", true);
        assertTrue(m.matches("pattern_154/file.txt"));
        assertFalse(m.matches("pattern_154/file.log"));
    }
    @Test
    public void testGlobCase_155() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_155/*.txt", true);
        assertTrue(m.matches("pattern_155/file.txt"));
        assertFalse(m.matches("pattern_155/file.log"));
    }
    @Test
    public void testGlobCase_156() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_156/*.txt", true);
        assertTrue(m.matches("pattern_156/file.txt"));
        assertFalse(m.matches("pattern_156/file.log"));
    }
    @Test
    public void testGlobCase_157() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_157/*.txt", true);
        assertTrue(m.matches("pattern_157/file.txt"));
        assertFalse(m.matches("pattern_157/file.log"));
    }
    @Test
    public void testGlobCase_158() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_158/*.txt", true);
        assertTrue(m.matches("pattern_158/file.txt"));
        assertFalse(m.matches("pattern_158/file.log"));
    }
    @Test
    public void testGlobCase_159() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_159/*.txt", true);
        assertTrue(m.matches("pattern_159/file.txt"));
        assertFalse(m.matches("pattern_159/file.log"));
    }
    @Test
    public void testGlobCase_160() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_160/*.txt", true);
        assertTrue(m.matches("pattern_160/file.txt"));
        assertFalse(m.matches("pattern_160/file.log"));
    }
    @Test
    public void testGlobCase_161() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_161/*.txt", true);
        assertTrue(m.matches("pattern_161/file.txt"));
        assertFalse(m.matches("pattern_161/file.log"));
    }
    @Test
    public void testGlobCase_162() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_162/*.txt", true);
        assertTrue(m.matches("pattern_162/file.txt"));
        assertFalse(m.matches("pattern_162/file.log"));
    }
    @Test
    public void testGlobCase_163() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_163/*.txt", true);
        assertTrue(m.matches("pattern_163/file.txt"));
        assertFalse(m.matches("pattern_163/file.log"));
    }
    @Test
    public void testGlobCase_164() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_164/*.txt", true);
        assertTrue(m.matches("pattern_164/file.txt"));
        assertFalse(m.matches("pattern_164/file.log"));
    }
    @Test
    public void testGlobCase_165() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_165/*.txt", true);
        assertTrue(m.matches("pattern_165/file.txt"));
        assertFalse(m.matches("pattern_165/file.log"));
    }
    @Test
    public void testGlobCase_166() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_166/*.txt", true);
        assertTrue(m.matches("pattern_166/file.txt"));
        assertFalse(m.matches("pattern_166/file.log"));
    }
    @Test
    public void testGlobCase_167() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_167/*.txt", true);
        assertTrue(m.matches("pattern_167/file.txt"));
        assertFalse(m.matches("pattern_167/file.log"));
    }
    @Test
    public void testGlobCase_168() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_168/*.txt", true);
        assertTrue(m.matches("pattern_168/file.txt"));
        assertFalse(m.matches("pattern_168/file.log"));
    }
    @Test
    public void testGlobCase_169() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_169/*.txt", true);
        assertTrue(m.matches("pattern_169/file.txt"));
        assertFalse(m.matches("pattern_169/file.log"));
    }
    @Test
    public void testGlobCase_170() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_170/*.txt", true);
        assertTrue(m.matches("pattern_170/file.txt"));
        assertFalse(m.matches("pattern_170/file.log"));
    }
    @Test
    public void testGlobCase_171() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_171/*.txt", true);
        assertTrue(m.matches("pattern_171/file.txt"));
        assertFalse(m.matches("pattern_171/file.log"));
    }
    @Test
    public void testGlobCase_172() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_172/*.txt", true);
        assertTrue(m.matches("pattern_172/file.txt"));
        assertFalse(m.matches("pattern_172/file.log"));
    }
    @Test
    public void testGlobCase_173() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_173/*.txt", true);
        assertTrue(m.matches("pattern_173/file.txt"));
        assertFalse(m.matches("pattern_173/file.log"));
    }
    @Test
    public void testGlobCase_174() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_174/*.txt", true);
        assertTrue(m.matches("pattern_174/file.txt"));
        assertFalse(m.matches("pattern_174/file.log"));
    }
    @Test
    public void testGlobCase_175() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_175/*.txt", true);
        assertTrue(m.matches("pattern_175/file.txt"));
        assertFalse(m.matches("pattern_175/file.log"));
    }
    @Test
    public void testGlobCase_176() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_176/*.txt", true);
        assertTrue(m.matches("pattern_176/file.txt"));
        assertFalse(m.matches("pattern_176/file.log"));
    }
    @Test
    public void testGlobCase_177() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_177/*.txt", true);
        assertTrue(m.matches("pattern_177/file.txt"));
        assertFalse(m.matches("pattern_177/file.log"));
    }
    @Test
    public void testGlobCase_178() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_178/*.txt", true);
        assertTrue(m.matches("pattern_178/file.txt"));
        assertFalse(m.matches("pattern_178/file.log"));
    }
    @Test
    public void testGlobCase_179() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_179/*.txt", true);
        assertTrue(m.matches("pattern_179/file.txt"));
        assertFalse(m.matches("pattern_179/file.log"));
    }
    @Test
    public void testGlobCase_180() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_180/*.txt", true);
        assertTrue(m.matches("pattern_180/file.txt"));
        assertFalse(m.matches("pattern_180/file.log"));
    }
    @Test
    public void testGlobCase_181() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_181/*.txt", true);
        assertTrue(m.matches("pattern_181/file.txt"));
        assertFalse(m.matches("pattern_181/file.log"));
    }
    @Test
    public void testGlobCase_182() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_182/*.txt", true);
        assertTrue(m.matches("pattern_182/file.txt"));
        assertFalse(m.matches("pattern_182/file.log"));
    }
    @Test
    public void testGlobCase_183() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_183/*.txt", true);
        assertTrue(m.matches("pattern_183/file.txt"));
        assertFalse(m.matches("pattern_183/file.log"));
    }
    @Test
    public void testGlobCase_184() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_184/*.txt", true);
        assertTrue(m.matches("pattern_184/file.txt"));
        assertFalse(m.matches("pattern_184/file.log"));
    }
    @Test
    public void testGlobCase_185() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_185/*.txt", true);
        assertTrue(m.matches("pattern_185/file.txt"));
        assertFalse(m.matches("pattern_185/file.log"));
    }
    @Test
    public void testGlobCase_186() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_186/*.txt", true);
        assertTrue(m.matches("pattern_186/file.txt"));
        assertFalse(m.matches("pattern_186/file.log"));
    }
    @Test
    public void testGlobCase_187() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_187/*.txt", true);
        assertTrue(m.matches("pattern_187/file.txt"));
        assertFalse(m.matches("pattern_187/file.log"));
    }
    @Test
    public void testGlobCase_188() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_188/*.txt", true);
        assertTrue(m.matches("pattern_188/file.txt"));
        assertFalse(m.matches("pattern_188/file.log"));
    }
    @Test
    public void testGlobCase_189() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_189/*.txt", true);
        assertTrue(m.matches("pattern_189/file.txt"));
        assertFalse(m.matches("pattern_189/file.log"));
    }
    @Test
    public void testGlobCase_190() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_190/*.txt", true);
        assertTrue(m.matches("pattern_190/file.txt"));
        assertFalse(m.matches("pattern_190/file.log"));
    }
    @Test
    public void testGlobCase_191() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_191/*.txt", true);
        assertTrue(m.matches("pattern_191/file.txt"));
        assertFalse(m.matches("pattern_191/file.log"));
    }
    @Test
    public void testGlobCase_192() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_192/*.txt", true);
        assertTrue(m.matches("pattern_192/file.txt"));
        assertFalse(m.matches("pattern_192/file.log"));
    }
    @Test
    public void testGlobCase_193() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_193/*.txt", true);
        assertTrue(m.matches("pattern_193/file.txt"));
        assertFalse(m.matches("pattern_193/file.log"));
    }
    @Test
    public void testGlobCase_194() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_194/*.txt", true);
        assertTrue(m.matches("pattern_194/file.txt"));
        assertFalse(m.matches("pattern_194/file.log"));
    }
    @Test
    public void testGlobCase_195() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_195/*.txt", true);
        assertTrue(m.matches("pattern_195/file.txt"));
        assertFalse(m.matches("pattern_195/file.log"));
    }
    @Test
    public void testGlobCase_196() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_196/*.txt", true);
        assertTrue(m.matches("pattern_196/file.txt"));
        assertFalse(m.matches("pattern_196/file.log"));
    }
    @Test
    public void testGlobCase_197() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_197/*.txt", true);
        assertTrue(m.matches("pattern_197/file.txt"));
        assertFalse(m.matches("pattern_197/file.log"));
    }
    @Test
    public void testGlobCase_198() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_198/*.txt", true);
        assertTrue(m.matches("pattern_198/file.txt"));
        assertFalse(m.matches("pattern_198/file.log"));
    }
    @Test
    public void testGlobCase_199() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_199/*.txt", true);
        assertTrue(m.matches("pattern_199/file.txt"));
        assertFalse(m.matches("pattern_199/file.log"));
    }
    @Test
    public void testGlobCase_200() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_200/*.txt", true);
        assertTrue(m.matches("pattern_200/file.txt"));
        assertFalse(m.matches("pattern_200/file.log"));
    }
    @Test
    public void testGlobCase_201() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_201/*.txt", true);
        assertTrue(m.matches("pattern_201/file.txt"));
        assertFalse(m.matches("pattern_201/file.log"));
    }
    @Test
    public void testGlobCase_202() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_202/*.txt", true);
        assertTrue(m.matches("pattern_202/file.txt"));
        assertFalse(m.matches("pattern_202/file.log"));
    }
    @Test
    public void testGlobCase_203() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_203/*.txt", true);
        assertTrue(m.matches("pattern_203/file.txt"));
        assertFalse(m.matches("pattern_203/file.log"));
    }
    @Test
    public void testGlobCase_204() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_204/*.txt", true);
        assertTrue(m.matches("pattern_204/file.txt"));
        assertFalse(m.matches("pattern_204/file.log"));
    }
    @Test
    public void testGlobCase_205() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_205/*.txt", true);
        assertTrue(m.matches("pattern_205/file.txt"));
        assertFalse(m.matches("pattern_205/file.log"));
    }
    @Test
    public void testGlobCase_206() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_206/*.txt", true);
        assertTrue(m.matches("pattern_206/file.txt"));
        assertFalse(m.matches("pattern_206/file.log"));
    }
    @Test
    public void testGlobCase_207() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_207/*.txt", true);
        assertTrue(m.matches("pattern_207/file.txt"));
        assertFalse(m.matches("pattern_207/file.log"));
    }
    @Test
    public void testGlobCase_208() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_208/*.txt", true);
        assertTrue(m.matches("pattern_208/file.txt"));
        assertFalse(m.matches("pattern_208/file.log"));
    }
    @Test
    public void testGlobCase_209() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_209/*.txt", true);
        assertTrue(m.matches("pattern_209/file.txt"));
        assertFalse(m.matches("pattern_209/file.log"));
    }
    @Test
    public void testGlobCase_210() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_210/*.txt", true);
        assertTrue(m.matches("pattern_210/file.txt"));
        assertFalse(m.matches("pattern_210/file.log"));
    }
    @Test
    public void testGlobCase_211() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_211/*.txt", true);
        assertTrue(m.matches("pattern_211/file.txt"));
        assertFalse(m.matches("pattern_211/file.log"));
    }
    @Test
    public void testGlobCase_212() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_212/*.txt", true);
        assertTrue(m.matches("pattern_212/file.txt"));
        assertFalse(m.matches("pattern_212/file.log"));
    }
    @Test
    public void testGlobCase_213() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_213/*.txt", true);
        assertTrue(m.matches("pattern_213/file.txt"));
        assertFalse(m.matches("pattern_213/file.log"));
    }
    @Test
    public void testGlobCase_214() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_214/*.txt", true);
        assertTrue(m.matches("pattern_214/file.txt"));
        assertFalse(m.matches("pattern_214/file.log"));
    }
    @Test
    public void testGlobCase_215() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_215/*.txt", true);
        assertTrue(m.matches("pattern_215/file.txt"));
        assertFalse(m.matches("pattern_215/file.log"));
    }
    @Test
    public void testGlobCase_216() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_216/*.txt", true);
        assertTrue(m.matches("pattern_216/file.txt"));
        assertFalse(m.matches("pattern_216/file.log"));
    }
    @Test
    public void testGlobCase_217() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_217/*.txt", true);
        assertTrue(m.matches("pattern_217/file.txt"));
        assertFalse(m.matches("pattern_217/file.log"));
    }
    @Test
    public void testGlobCase_218() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_218/*.txt", true);
        assertTrue(m.matches("pattern_218/file.txt"));
        assertFalse(m.matches("pattern_218/file.log"));
    }
    @Test
    public void testGlobCase_219() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_219/*.txt", true);
        assertTrue(m.matches("pattern_219/file.txt"));
        assertFalse(m.matches("pattern_219/file.log"));
    }
    @Test
    public void testGlobCase_220() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_220/*.txt", true);
        assertTrue(m.matches("pattern_220/file.txt"));
        assertFalse(m.matches("pattern_220/file.log"));
    }
    @Test
    public void testGlobCase_221() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_221/*.txt", true);
        assertTrue(m.matches("pattern_221/file.txt"));
        assertFalse(m.matches("pattern_221/file.log"));
    }
    @Test
    public void testGlobCase_222() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_222/*.txt", true);
        assertTrue(m.matches("pattern_222/file.txt"));
        assertFalse(m.matches("pattern_222/file.log"));
    }
    @Test
    public void testGlobCase_223() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_223/*.txt", true);
        assertTrue(m.matches("pattern_223/file.txt"));
        assertFalse(m.matches("pattern_223/file.log"));
    }
    @Test
    public void testGlobCase_224() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_224/*.txt", true);
        assertTrue(m.matches("pattern_224/file.txt"));
        assertFalse(m.matches("pattern_224/file.log"));
    }
    @Test
    public void testGlobCase_225() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_225/*.txt", true);
        assertTrue(m.matches("pattern_225/file.txt"));
        assertFalse(m.matches("pattern_225/file.log"));
    }
    @Test
    public void testGlobCase_226() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_226/*.txt", true);
        assertTrue(m.matches("pattern_226/file.txt"));
        assertFalse(m.matches("pattern_226/file.log"));
    }
    @Test
    public void testGlobCase_227() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_227/*.txt", true);
        assertTrue(m.matches("pattern_227/file.txt"));
        assertFalse(m.matches("pattern_227/file.log"));
    }
    @Test
    public void testGlobCase_228() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_228/*.txt", true);
        assertTrue(m.matches("pattern_228/file.txt"));
        assertFalse(m.matches("pattern_228/file.log"));
    }
    @Test
    public void testGlobCase_229() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_229/*.txt", true);
        assertTrue(m.matches("pattern_229/file.txt"));
        assertFalse(m.matches("pattern_229/file.log"));
    }
    @Test
    public void testGlobCase_230() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_230/*.txt", true);
        assertTrue(m.matches("pattern_230/file.txt"));
        assertFalse(m.matches("pattern_230/file.log"));
    }
    @Test
    public void testGlobCase_231() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_231/*.txt", true);
        assertTrue(m.matches("pattern_231/file.txt"));
        assertFalse(m.matches("pattern_231/file.log"));
    }
    @Test
    public void testGlobCase_232() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_232/*.txt", true);
        assertTrue(m.matches("pattern_232/file.txt"));
        assertFalse(m.matches("pattern_232/file.log"));
    }
    @Test
    public void testGlobCase_233() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_233/*.txt", true);
        assertTrue(m.matches("pattern_233/file.txt"));
        assertFalse(m.matches("pattern_233/file.log"));
    }
    @Test
    public void testGlobCase_234() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_234/*.txt", true);
        assertTrue(m.matches("pattern_234/file.txt"));
        assertFalse(m.matches("pattern_234/file.log"));
    }
    @Test
    public void testGlobCase_235() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_235/*.txt", true);
        assertTrue(m.matches("pattern_235/file.txt"));
        assertFalse(m.matches("pattern_235/file.log"));
    }
    @Test
    public void testGlobCase_236() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_236/*.txt", true);
        assertTrue(m.matches("pattern_236/file.txt"));
        assertFalse(m.matches("pattern_236/file.log"));
    }
    @Test
    public void testGlobCase_237() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_237/*.txt", true);
        assertTrue(m.matches("pattern_237/file.txt"));
        assertFalse(m.matches("pattern_237/file.log"));
    }
    @Test
    public void testGlobCase_238() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_238/*.txt", true);
        assertTrue(m.matches("pattern_238/file.txt"));
        assertFalse(m.matches("pattern_238/file.log"));
    }
    @Test
    public void testGlobCase_239() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_239/*.txt", true);
        assertTrue(m.matches("pattern_239/file.txt"));
        assertFalse(m.matches("pattern_239/file.log"));
    }
    @Test
    public void testGlobCase_240() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_240/*.txt", true);
        assertTrue(m.matches("pattern_240/file.txt"));
        assertFalse(m.matches("pattern_240/file.log"));
    }
    @Test
    public void testGlobCase_241() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_241/*.txt", true);
        assertTrue(m.matches("pattern_241/file.txt"));
        assertFalse(m.matches("pattern_241/file.log"));
    }
    @Test
    public void testGlobCase_242() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_242/*.txt", true);
        assertTrue(m.matches("pattern_242/file.txt"));
        assertFalse(m.matches("pattern_242/file.log"));
    }
    @Test
    public void testGlobCase_243() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_243/*.txt", true);
        assertTrue(m.matches("pattern_243/file.txt"));
        assertFalse(m.matches("pattern_243/file.log"));
    }
    @Test
    public void testGlobCase_244() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_244/*.txt", true);
        assertTrue(m.matches("pattern_244/file.txt"));
        assertFalse(m.matches("pattern_244/file.log"));
    }
    @Test
    public void testGlobCase_245() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_245/*.txt", true);
        assertTrue(m.matches("pattern_245/file.txt"));
        assertFalse(m.matches("pattern_245/file.log"));
    }
    @Test
    public void testGlobCase_246() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_246/*.txt", true);
        assertTrue(m.matches("pattern_246/file.txt"));
        assertFalse(m.matches("pattern_246/file.log"));
    }
    @Test
    public void testGlobCase_247() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_247/*.txt", true);
        assertTrue(m.matches("pattern_247/file.txt"));
        assertFalse(m.matches("pattern_247/file.log"));
    }
    @Test
    public void testGlobCase_248() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_248/*.txt", true);
        assertTrue(m.matches("pattern_248/file.txt"));
        assertFalse(m.matches("pattern_248/file.log"));
    }
    @Test
    public void testGlobCase_249() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_249/*.txt", true);
        assertTrue(m.matches("pattern_249/file.txt"));
        assertFalse(m.matches("pattern_249/file.log"));
    }
    @Test
    public void testGlobCase_250() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_250/*.txt", true);
        assertTrue(m.matches("pattern_250/file.txt"));
        assertFalse(m.matches("pattern_250/file.log"));
    }
    @Test
    public void testGlobCase_251() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_251/*.txt", true);
        assertTrue(m.matches("pattern_251/file.txt"));
        assertFalse(m.matches("pattern_251/file.log"));
    }
    @Test
    public void testGlobCase_252() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_252/*.txt", true);
        assertTrue(m.matches("pattern_252/file.txt"));
        assertFalse(m.matches("pattern_252/file.log"));
    }
    @Test
    public void testGlobCase_253() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_253/*.txt", true);
        assertTrue(m.matches("pattern_253/file.txt"));
        assertFalse(m.matches("pattern_253/file.log"));
    }
    @Test
    public void testGlobCase_254() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_254/*.txt", true);
        assertTrue(m.matches("pattern_254/file.txt"));
        assertFalse(m.matches("pattern_254/file.log"));
    }
    @Test
    public void testGlobCase_255() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_255/*.txt", true);
        assertTrue(m.matches("pattern_255/file.txt"));
        assertFalse(m.matches("pattern_255/file.log"));
    }
    @Test
    public void testGlobCase_256() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_256/*.txt", true);
        assertTrue(m.matches("pattern_256/file.txt"));
        assertFalse(m.matches("pattern_256/file.log"));
    }
    @Test
    public void testGlobCase_257() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_257/*.txt", true);
        assertTrue(m.matches("pattern_257/file.txt"));
        assertFalse(m.matches("pattern_257/file.log"));
    }
    @Test
    public void testGlobCase_258() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_258/*.txt", true);
        assertTrue(m.matches("pattern_258/file.txt"));
        assertFalse(m.matches("pattern_258/file.log"));
    }
    @Test
    public void testGlobCase_259() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_259/*.txt", true);
        assertTrue(m.matches("pattern_259/file.txt"));
        assertFalse(m.matches("pattern_259/file.log"));
    }
    @Test
    public void testGlobCase_260() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_260/*.txt", true);
        assertTrue(m.matches("pattern_260/file.txt"));
        assertFalse(m.matches("pattern_260/file.log"));
    }
    @Test
    public void testGlobCase_261() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_261/*.txt", true);
        assertTrue(m.matches("pattern_261/file.txt"));
        assertFalse(m.matches("pattern_261/file.log"));
    }
    @Test
    public void testGlobCase_262() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_262/*.txt", true);
        assertTrue(m.matches("pattern_262/file.txt"));
        assertFalse(m.matches("pattern_262/file.log"));
    }
    @Test
    public void testGlobCase_263() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_263/*.txt", true);
        assertTrue(m.matches("pattern_263/file.txt"));
        assertFalse(m.matches("pattern_263/file.log"));
    }
    @Test
    public void testGlobCase_264() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_264/*.txt", true);
        assertTrue(m.matches("pattern_264/file.txt"));
        assertFalse(m.matches("pattern_264/file.log"));
    }
    @Test
    public void testGlobCase_265() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_265/*.txt", true);
        assertTrue(m.matches("pattern_265/file.txt"));
        assertFalse(m.matches("pattern_265/file.log"));
    }
    @Test
    public void testGlobCase_266() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_266/*.txt", true);
        assertTrue(m.matches("pattern_266/file.txt"));
        assertFalse(m.matches("pattern_266/file.log"));
    }
    @Test
    public void testGlobCase_267() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_267/*.txt", true);
        assertTrue(m.matches("pattern_267/file.txt"));
        assertFalse(m.matches("pattern_267/file.log"));
    }
    @Test
    public void testGlobCase_268() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_268/*.txt", true);
        assertTrue(m.matches("pattern_268/file.txt"));
        assertFalse(m.matches("pattern_268/file.log"));
    }
    @Test
    public void testGlobCase_269() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_269/*.txt", true);
        assertTrue(m.matches("pattern_269/file.txt"));
        assertFalse(m.matches("pattern_269/file.log"));
    }
    @Test
    public void testGlobCase_270() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_270/*.txt", true);
        assertTrue(m.matches("pattern_270/file.txt"));
        assertFalse(m.matches("pattern_270/file.log"));
    }
    @Test
    public void testGlobCase_271() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_271/*.txt", true);
        assertTrue(m.matches("pattern_271/file.txt"));
        assertFalse(m.matches("pattern_271/file.log"));
    }
    @Test
    public void testGlobCase_272() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_272/*.txt", true);
        assertTrue(m.matches("pattern_272/file.txt"));
        assertFalse(m.matches("pattern_272/file.log"));
    }
    @Test
    public void testGlobCase_273() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_273/*.txt", true);
        assertTrue(m.matches("pattern_273/file.txt"));
        assertFalse(m.matches("pattern_273/file.log"));
    }
    @Test
    public void testGlobCase_274() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_274/*.txt", true);
        assertTrue(m.matches("pattern_274/file.txt"));
        assertFalse(m.matches("pattern_274/file.log"));
    }
    @Test
    public void testGlobCase_275() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_275/*.txt", true);
        assertTrue(m.matches("pattern_275/file.txt"));
        assertFalse(m.matches("pattern_275/file.log"));
    }
    @Test
    public void testGlobCase_276() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_276/*.txt", true);
        assertTrue(m.matches("pattern_276/file.txt"));
        assertFalse(m.matches("pattern_276/file.log"));
    }
    @Test
    public void testGlobCase_277() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_277/*.txt", true);
        assertTrue(m.matches("pattern_277/file.txt"));
        assertFalse(m.matches("pattern_277/file.log"));
    }
    @Test
    public void testGlobCase_278() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_278/*.txt", true);
        assertTrue(m.matches("pattern_278/file.txt"));
        assertFalse(m.matches("pattern_278/file.log"));
    }
    @Test
    public void testGlobCase_279() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_279/*.txt", true);
        assertTrue(m.matches("pattern_279/file.txt"));
        assertFalse(m.matches("pattern_279/file.log"));
    }
    @Test
    public void testGlobCase_280() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_280/*.txt", true);
        assertTrue(m.matches("pattern_280/file.txt"));
        assertFalse(m.matches("pattern_280/file.log"));
    }
    @Test
    public void testGlobCase_281() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_281/*.txt", true);
        assertTrue(m.matches("pattern_281/file.txt"));
        assertFalse(m.matches("pattern_281/file.log"));
    }
    @Test
    public void testGlobCase_282() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_282/*.txt", true);
        assertTrue(m.matches("pattern_282/file.txt"));
        assertFalse(m.matches("pattern_282/file.log"));
    }
    @Test
    public void testGlobCase_283() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_283/*.txt", true);
        assertTrue(m.matches("pattern_283/file.txt"));
        assertFalse(m.matches("pattern_283/file.log"));
    }
    @Test
    public void testGlobCase_284() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_284/*.txt", true);
        assertTrue(m.matches("pattern_284/file.txt"));
        assertFalse(m.matches("pattern_284/file.log"));
    }
    @Test
    public void testGlobCase_285() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_285/*.txt", true);
        assertTrue(m.matches("pattern_285/file.txt"));
        assertFalse(m.matches("pattern_285/file.log"));
    }
    @Test
    public void testGlobCase_286() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_286/*.txt", true);
        assertTrue(m.matches("pattern_286/file.txt"));
        assertFalse(m.matches("pattern_286/file.log"));
    }
    @Test
    public void testGlobCase_287() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_287/*.txt", true);
        assertTrue(m.matches("pattern_287/file.txt"));
        assertFalse(m.matches("pattern_287/file.log"));
    }
    @Test
    public void testGlobCase_288() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_288/*.txt", true);
        assertTrue(m.matches("pattern_288/file.txt"));
        assertFalse(m.matches("pattern_288/file.log"));
    }
    @Test
    public void testGlobCase_289() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_289/*.txt", true);
        assertTrue(m.matches("pattern_289/file.txt"));
        assertFalse(m.matches("pattern_289/file.log"));
    }
    @Test
    public void testGlobCase_290() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_290/*.txt", true);
        assertTrue(m.matches("pattern_290/file.txt"));
        assertFalse(m.matches("pattern_290/file.log"));
    }
    @Test
    public void testGlobCase_291() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_291/*.txt", true);
        assertTrue(m.matches("pattern_291/file.txt"));
        assertFalse(m.matches("pattern_291/file.log"));
    }
    @Test
    public void testGlobCase_292() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_292/*.txt", true);
        assertTrue(m.matches("pattern_292/file.txt"));
        assertFalse(m.matches("pattern_292/file.log"));
    }
    @Test
    public void testGlobCase_293() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_293/*.txt", true);
        assertTrue(m.matches("pattern_293/file.txt"));
        assertFalse(m.matches("pattern_293/file.log"));
    }
    @Test
    public void testGlobCase_294() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_294/*.txt", true);
        assertTrue(m.matches("pattern_294/file.txt"));
        assertFalse(m.matches("pattern_294/file.log"));
    }
    @Test
    public void testGlobCase_295() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_295/*.txt", true);
        assertTrue(m.matches("pattern_295/file.txt"));
        assertFalse(m.matches("pattern_295/file.log"));
    }
    @Test
    public void testGlobCase_296() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_296/*.txt", true);
        assertTrue(m.matches("pattern_296/file.txt"));
        assertFalse(m.matches("pattern_296/file.log"));
    }
    @Test
    public void testGlobCase_297() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_297/*.txt", true);
        assertTrue(m.matches("pattern_297/file.txt"));
        assertFalse(m.matches("pattern_297/file.log"));
    }
    @Test
    public void testGlobCase_298() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_298/*.txt", true);
        assertTrue(m.matches("pattern_298/file.txt"));
        assertFalse(m.matches("pattern_298/file.log"));
    }
    @Test
    public void testGlobCase_299() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_299/*.txt", true);
        assertTrue(m.matches("pattern_299/file.txt"));
        assertFalse(m.matches("pattern_299/file.log"));
    }
    @Test
    public void testGlobCase_300() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_300/*.txt", true);
        assertTrue(m.matches("pattern_300/file.txt"));
        assertFalse(m.matches("pattern_300/file.log"));
    }
    @Test
    public void testGlobCase_301() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_301/*.txt", true);
        assertTrue(m.matches("pattern_301/file.txt"));
        assertFalse(m.matches("pattern_301/file.log"));
    }
    @Test
    public void testGlobCase_302() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_302/*.txt", true);
        assertTrue(m.matches("pattern_302/file.txt"));
        assertFalse(m.matches("pattern_302/file.log"));
    }
    @Test
    public void testGlobCase_303() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_303/*.txt", true);
        assertTrue(m.matches("pattern_303/file.txt"));
        assertFalse(m.matches("pattern_303/file.log"));
    }
    @Test
    public void testGlobCase_304() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_304/*.txt", true);
        assertTrue(m.matches("pattern_304/file.txt"));
        assertFalse(m.matches("pattern_304/file.log"));
    }
    @Test
    public void testGlobCase_305() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_305/*.txt", true);
        assertTrue(m.matches("pattern_305/file.txt"));
        assertFalse(m.matches("pattern_305/file.log"));
    }
    @Test
    public void testGlobCase_306() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_306/*.txt", true);
        assertTrue(m.matches("pattern_306/file.txt"));
        assertFalse(m.matches("pattern_306/file.log"));
    }
    @Test
    public void testGlobCase_307() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_307/*.txt", true);
        assertTrue(m.matches("pattern_307/file.txt"));
        assertFalse(m.matches("pattern_307/file.log"));
    }
    @Test
    public void testGlobCase_308() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_308/*.txt", true);
        assertTrue(m.matches("pattern_308/file.txt"));
        assertFalse(m.matches("pattern_308/file.log"));
    }
    @Test
    public void testGlobCase_309() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_309/*.txt", true);
        assertTrue(m.matches("pattern_309/file.txt"));
        assertFalse(m.matches("pattern_309/file.log"));
    }
    @Test
    public void testGlobCase_310() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_310/*.txt", true);
        assertTrue(m.matches("pattern_310/file.txt"));
        assertFalse(m.matches("pattern_310/file.log"));
    }
    @Test
    public void testGlobCase_311() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_311/*.txt", true);
        assertTrue(m.matches("pattern_311/file.txt"));
        assertFalse(m.matches("pattern_311/file.log"));
    }
    @Test
    public void testGlobCase_312() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_312/*.txt", true);
        assertTrue(m.matches("pattern_312/file.txt"));
        assertFalse(m.matches("pattern_312/file.log"));
    }
    @Test
    public void testGlobCase_313() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_313/*.txt", true);
        assertTrue(m.matches("pattern_313/file.txt"));
        assertFalse(m.matches("pattern_313/file.log"));
    }
    @Test
    public void testGlobCase_314() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_314/*.txt", true);
        assertTrue(m.matches("pattern_314/file.txt"));
        assertFalse(m.matches("pattern_314/file.log"));
    }
    @Test
    public void testGlobCase_315() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_315/*.txt", true);
        assertTrue(m.matches("pattern_315/file.txt"));
        assertFalse(m.matches("pattern_315/file.log"));
    }
    @Test
    public void testGlobCase_316() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_316/*.txt", true);
        assertTrue(m.matches("pattern_316/file.txt"));
        assertFalse(m.matches("pattern_316/file.log"));
    }
    @Test
    public void testGlobCase_317() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_317/*.txt", true);
        assertTrue(m.matches("pattern_317/file.txt"));
        assertFalse(m.matches("pattern_317/file.log"));
    }
    @Test
    public void testGlobCase_318() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_318/*.txt", true);
        assertTrue(m.matches("pattern_318/file.txt"));
        assertFalse(m.matches("pattern_318/file.log"));
    }
    @Test
    public void testGlobCase_319() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_319/*.txt", true);
        assertTrue(m.matches("pattern_319/file.txt"));
        assertFalse(m.matches("pattern_319/file.log"));
    }
    @Test
    public void testGlobCase_320() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_320/*.txt", true);
        assertTrue(m.matches("pattern_320/file.txt"));
        assertFalse(m.matches("pattern_320/file.log"));
    }
    @Test
    public void testGlobCase_321() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_321/*.txt", true);
        assertTrue(m.matches("pattern_321/file.txt"));
        assertFalse(m.matches("pattern_321/file.log"));
    }
    @Test
    public void testGlobCase_322() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_322/*.txt", true);
        assertTrue(m.matches("pattern_322/file.txt"));
        assertFalse(m.matches("pattern_322/file.log"));
    }
    @Test
    public void testGlobCase_323() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_323/*.txt", true);
        assertTrue(m.matches("pattern_323/file.txt"));
        assertFalse(m.matches("pattern_323/file.log"));
    }
    @Test
    public void testGlobCase_324() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_324/*.txt", true);
        assertTrue(m.matches("pattern_324/file.txt"));
        assertFalse(m.matches("pattern_324/file.log"));
    }
    @Test
    public void testGlobCase_325() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_325/*.txt", true);
        assertTrue(m.matches("pattern_325/file.txt"));
        assertFalse(m.matches("pattern_325/file.log"));
    }
    @Test
    public void testGlobCase_326() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_326/*.txt", true);
        assertTrue(m.matches("pattern_326/file.txt"));
        assertFalse(m.matches("pattern_326/file.log"));
    }
    @Test
    public void testGlobCase_327() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_327/*.txt", true);
        assertTrue(m.matches("pattern_327/file.txt"));
        assertFalse(m.matches("pattern_327/file.log"));
    }
    @Test
    public void testGlobCase_328() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_328/*.txt", true);
        assertTrue(m.matches("pattern_328/file.txt"));
        assertFalse(m.matches("pattern_328/file.log"));
    }
    @Test
    public void testGlobCase_329() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_329/*.txt", true);
        assertTrue(m.matches("pattern_329/file.txt"));
        assertFalse(m.matches("pattern_329/file.log"));
    }
    @Test
    public void testGlobCase_330() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_330/*.txt", true);
        assertTrue(m.matches("pattern_330/file.txt"));
        assertFalse(m.matches("pattern_330/file.log"));
    }
    @Test
    public void testGlobCase_331() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_331/*.txt", true);
        assertTrue(m.matches("pattern_331/file.txt"));
        assertFalse(m.matches("pattern_331/file.log"));
    }
    @Test
    public void testGlobCase_332() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_332/*.txt", true);
        assertTrue(m.matches("pattern_332/file.txt"));
        assertFalse(m.matches("pattern_332/file.log"));
    }
    @Test
    public void testGlobCase_333() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_333/*.txt", true);
        assertTrue(m.matches("pattern_333/file.txt"));
        assertFalse(m.matches("pattern_333/file.log"));
    }
    @Test
    public void testGlobCase_334() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_334/*.txt", true);
        assertTrue(m.matches("pattern_334/file.txt"));
        assertFalse(m.matches("pattern_334/file.log"));
    }
    @Test
    public void testGlobCase_335() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_335/*.txt", true);
        assertTrue(m.matches("pattern_335/file.txt"));
        assertFalse(m.matches("pattern_335/file.log"));
    }
    @Test
    public void testGlobCase_336() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_336/*.txt", true);
        assertTrue(m.matches("pattern_336/file.txt"));
        assertFalse(m.matches("pattern_336/file.log"));
    }
    @Test
    public void testGlobCase_337() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_337/*.txt", true);
        assertTrue(m.matches("pattern_337/file.txt"));
        assertFalse(m.matches("pattern_337/file.log"));
    }
    @Test
    public void testGlobCase_338() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_338/*.txt", true);
        assertTrue(m.matches("pattern_338/file.txt"));
        assertFalse(m.matches("pattern_338/file.log"));
    }
    @Test
    public void testGlobCase_339() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_339/*.txt", true);
        assertTrue(m.matches("pattern_339/file.txt"));
        assertFalse(m.matches("pattern_339/file.log"));
    }
    @Test
    public void testGlobCase_340() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_340/*.txt", true);
        assertTrue(m.matches("pattern_340/file.txt"));
        assertFalse(m.matches("pattern_340/file.log"));
    }
    @Test
    public void testGlobCase_341() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_341/*.txt", true);
        assertTrue(m.matches("pattern_341/file.txt"));
        assertFalse(m.matches("pattern_341/file.log"));
    }
    @Test
    public void testGlobCase_342() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_342/*.txt", true);
        assertTrue(m.matches("pattern_342/file.txt"));
        assertFalse(m.matches("pattern_342/file.log"));
    }
    @Test
    public void testGlobCase_343() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_343/*.txt", true);
        assertTrue(m.matches("pattern_343/file.txt"));
        assertFalse(m.matches("pattern_343/file.log"));
    }
    @Test
    public void testGlobCase_344() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_344/*.txt", true);
        assertTrue(m.matches("pattern_344/file.txt"));
        assertFalse(m.matches("pattern_344/file.log"));
    }
    @Test
    public void testGlobCase_345() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_345/*.txt", true);
        assertTrue(m.matches("pattern_345/file.txt"));
        assertFalse(m.matches("pattern_345/file.log"));
    }
    @Test
    public void testGlobCase_346() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_346/*.txt", true);
        assertTrue(m.matches("pattern_346/file.txt"));
        assertFalse(m.matches("pattern_346/file.log"));
    }
    @Test
    public void testGlobCase_347() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_347/*.txt", true);
        assertTrue(m.matches("pattern_347/file.txt"));
        assertFalse(m.matches("pattern_347/file.log"));
    }
    @Test
    public void testGlobCase_348() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_348/*.txt", true);
        assertTrue(m.matches("pattern_348/file.txt"));
        assertFalse(m.matches("pattern_348/file.log"));
    }
    @Test
    public void testGlobCase_349() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_349/*.txt", true);
        assertTrue(m.matches("pattern_349/file.txt"));
        assertFalse(m.matches("pattern_349/file.log"));
    }
    @Test
    public void testGlobCase_350() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_350/*.txt", true);
        assertTrue(m.matches("pattern_350/file.txt"));
        assertFalse(m.matches("pattern_350/file.log"));
    }
    @Test
    public void testGlobCase_351() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_351/*.txt", true);
        assertTrue(m.matches("pattern_351/file.txt"));
        assertFalse(m.matches("pattern_351/file.log"));
    }
    @Test
    public void testGlobCase_352() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_352/*.txt", true);
        assertTrue(m.matches("pattern_352/file.txt"));
        assertFalse(m.matches("pattern_352/file.log"));
    }
    @Test
    public void testGlobCase_353() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_353/*.txt", true);
        assertTrue(m.matches("pattern_353/file.txt"));
        assertFalse(m.matches("pattern_353/file.log"));
    }
    @Test
    public void testGlobCase_354() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_354/*.txt", true);
        assertTrue(m.matches("pattern_354/file.txt"));
        assertFalse(m.matches("pattern_354/file.log"));
    }
    @Test
    public void testGlobCase_355() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_355/*.txt", true);
        assertTrue(m.matches("pattern_355/file.txt"));
        assertFalse(m.matches("pattern_355/file.log"));
    }
    @Test
    public void testGlobCase_356() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_356/*.txt", true);
        assertTrue(m.matches("pattern_356/file.txt"));
        assertFalse(m.matches("pattern_356/file.log"));
    }
    @Test
    public void testGlobCase_357() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_357/*.txt", true);
        assertTrue(m.matches("pattern_357/file.txt"));
        assertFalse(m.matches("pattern_357/file.log"));
    }
    @Test
    public void testGlobCase_358() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_358/*.txt", true);
        assertTrue(m.matches("pattern_358/file.txt"));
        assertFalse(m.matches("pattern_358/file.log"));
    }
    @Test
    public void testGlobCase_359() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_359/*.txt", true);
        assertTrue(m.matches("pattern_359/file.txt"));
        assertFalse(m.matches("pattern_359/file.log"));
    }
    @Test
    public void testGlobCase_360() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_360/*.txt", true);
        assertTrue(m.matches("pattern_360/file.txt"));
        assertFalse(m.matches("pattern_360/file.log"));
    }
    @Test
    public void testGlobCase_361() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_361/*.txt", true);
        assertTrue(m.matches("pattern_361/file.txt"));
        assertFalse(m.matches("pattern_361/file.log"));
    }
    @Test
    public void testGlobCase_362() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_362/*.txt", true);
        assertTrue(m.matches("pattern_362/file.txt"));
        assertFalse(m.matches("pattern_362/file.log"));
    }
    @Test
    public void testGlobCase_363() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_363/*.txt", true);
        assertTrue(m.matches("pattern_363/file.txt"));
        assertFalse(m.matches("pattern_363/file.log"));
    }
    @Test
    public void testGlobCase_364() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_364/*.txt", true);
        assertTrue(m.matches("pattern_364/file.txt"));
        assertFalse(m.matches("pattern_364/file.log"));
    }
    @Test
    public void testGlobCase_365() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_365/*.txt", true);
        assertTrue(m.matches("pattern_365/file.txt"));
        assertFalse(m.matches("pattern_365/file.log"));
    }
    @Test
    public void testGlobCase_366() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_366/*.txt", true);
        assertTrue(m.matches("pattern_366/file.txt"));
        assertFalse(m.matches("pattern_366/file.log"));
    }
    @Test
    public void testGlobCase_367() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_367/*.txt", true);
        assertTrue(m.matches("pattern_367/file.txt"));
        assertFalse(m.matches("pattern_367/file.log"));
    }
    @Test
    public void testGlobCase_368() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_368/*.txt", true);
        assertTrue(m.matches("pattern_368/file.txt"));
        assertFalse(m.matches("pattern_368/file.log"));
    }
    @Test
    public void testGlobCase_369() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_369/*.txt", true);
        assertTrue(m.matches("pattern_369/file.txt"));
        assertFalse(m.matches("pattern_369/file.log"));
    }
    @Test
    public void testGlobCase_370() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_370/*.txt", true);
        assertTrue(m.matches("pattern_370/file.txt"));
        assertFalse(m.matches("pattern_370/file.log"));
    }
    @Test
    public void testGlobCase_371() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_371/*.txt", true);
        assertTrue(m.matches("pattern_371/file.txt"));
        assertFalse(m.matches("pattern_371/file.log"));
    }
    @Test
    public void testGlobCase_372() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_372/*.txt", true);
        assertTrue(m.matches("pattern_372/file.txt"));
        assertFalse(m.matches("pattern_372/file.log"));
    }
    @Test
    public void testGlobCase_373() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_373/*.txt", true);
        assertTrue(m.matches("pattern_373/file.txt"));
        assertFalse(m.matches("pattern_373/file.log"));
    }
    @Test
    public void testGlobCase_374() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_374/*.txt", true);
        assertTrue(m.matches("pattern_374/file.txt"));
        assertFalse(m.matches("pattern_374/file.log"));
    }
    @Test
    public void testGlobCase_375() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_375/*.txt", true);
        assertTrue(m.matches("pattern_375/file.txt"));
        assertFalse(m.matches("pattern_375/file.log"));
    }
    @Test
    public void testGlobCase_376() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_376/*.txt", true);
        assertTrue(m.matches("pattern_376/file.txt"));
        assertFalse(m.matches("pattern_376/file.log"));
    }
    @Test
    public void testGlobCase_377() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_377/*.txt", true);
        assertTrue(m.matches("pattern_377/file.txt"));
        assertFalse(m.matches("pattern_377/file.log"));
    }
    @Test
    public void testGlobCase_378() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_378/*.txt", true);
        assertTrue(m.matches("pattern_378/file.txt"));
        assertFalse(m.matches("pattern_378/file.log"));
    }
    @Test
    public void testGlobCase_379() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_379/*.txt", true);
        assertTrue(m.matches("pattern_379/file.txt"));
        assertFalse(m.matches("pattern_379/file.log"));
    }
    @Test
    public void testGlobCase_380() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_380/*.txt", true);
        assertTrue(m.matches("pattern_380/file.txt"));
        assertFalse(m.matches("pattern_380/file.log"));
    }
    @Test
    public void testGlobCase_381() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_381/*.txt", true);
        assertTrue(m.matches("pattern_381/file.txt"));
        assertFalse(m.matches("pattern_381/file.log"));
    }
    @Test
    public void testGlobCase_382() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_382/*.txt", true);
        assertTrue(m.matches("pattern_382/file.txt"));
        assertFalse(m.matches("pattern_382/file.log"));
    }
    @Test
    public void testGlobCase_383() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_383/*.txt", true);
        assertTrue(m.matches("pattern_383/file.txt"));
        assertFalse(m.matches("pattern_383/file.log"));
    }
    @Test
    public void testGlobCase_384() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_384/*.txt", true);
        assertTrue(m.matches("pattern_384/file.txt"));
        assertFalse(m.matches("pattern_384/file.log"));
    }
    @Test
    public void testGlobCase_385() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_385/*.txt", true);
        assertTrue(m.matches("pattern_385/file.txt"));
        assertFalse(m.matches("pattern_385/file.log"));
    }
    @Test
    public void testGlobCase_386() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_386/*.txt", true);
        assertTrue(m.matches("pattern_386/file.txt"));
        assertFalse(m.matches("pattern_386/file.log"));
    }
    @Test
    public void testGlobCase_387() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_387/*.txt", true);
        assertTrue(m.matches("pattern_387/file.txt"));
        assertFalse(m.matches("pattern_387/file.log"));
    }
    @Test
    public void testGlobCase_388() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_388/*.txt", true);
        assertTrue(m.matches("pattern_388/file.txt"));
        assertFalse(m.matches("pattern_388/file.log"));
    }
    @Test
    public void testGlobCase_389() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_389/*.txt", true);
        assertTrue(m.matches("pattern_389/file.txt"));
        assertFalse(m.matches("pattern_389/file.log"));
    }
    @Test
    public void testGlobCase_390() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_390/*.txt", true);
        assertTrue(m.matches("pattern_390/file.txt"));
        assertFalse(m.matches("pattern_390/file.log"));
    }
    @Test
    public void testGlobCase_391() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_391/*.txt", true);
        assertTrue(m.matches("pattern_391/file.txt"));
        assertFalse(m.matches("pattern_391/file.log"));
    }
    @Test
    public void testGlobCase_392() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_392/*.txt", true);
        assertTrue(m.matches("pattern_392/file.txt"));
        assertFalse(m.matches("pattern_392/file.log"));
    }
    @Test
    public void testGlobCase_393() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_393/*.txt", true);
        assertTrue(m.matches("pattern_393/file.txt"));
        assertFalse(m.matches("pattern_393/file.log"));
    }
    @Test
    public void testGlobCase_394() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_394/*.txt", true);
        assertTrue(m.matches("pattern_394/file.txt"));
        assertFalse(m.matches("pattern_394/file.log"));
    }
    @Test
    public void testGlobCase_395() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_395/*.txt", true);
        assertTrue(m.matches("pattern_395/file.txt"));
        assertFalse(m.matches("pattern_395/file.log"));
    }
    @Test
    public void testGlobCase_396() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_396/*.txt", true);
        assertTrue(m.matches("pattern_396/file.txt"));
        assertFalse(m.matches("pattern_396/file.log"));
    }
    @Test
    public void testGlobCase_397() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_397/*.txt", true);
        assertTrue(m.matches("pattern_397/file.txt"));
        assertFalse(m.matches("pattern_397/file.log"));
    }
    @Test
    public void testGlobCase_398() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_398/*.txt", true);
        assertTrue(m.matches("pattern_398/file.txt"));
        assertFalse(m.matches("pattern_398/file.log"));
    }
    @Test
    public void testGlobCase_399() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_399/*.txt", true);
        assertTrue(m.matches("pattern_399/file.txt"));
        assertFalse(m.matches("pattern_399/file.log"));
    }
    @Test
    public void testGlobCase_400() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_400/*.txt", true);
        assertTrue(m.matches("pattern_400/file.txt"));
        assertFalse(m.matches("pattern_400/file.log"));
    }
    @Test
    public void testGlobCase_401() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_401/*.txt", true);
        assertTrue(m.matches("pattern_401/file.txt"));
        assertFalse(m.matches("pattern_401/file.log"));
    }
    @Test
    public void testGlobCase_402() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_402/*.txt", true);
        assertTrue(m.matches("pattern_402/file.txt"));
        assertFalse(m.matches("pattern_402/file.log"));
    }
    @Test
    public void testGlobCase_403() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_403/*.txt", true);
        assertTrue(m.matches("pattern_403/file.txt"));
        assertFalse(m.matches("pattern_403/file.log"));
    }
    @Test
    public void testGlobCase_404() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_404/*.txt", true);
        assertTrue(m.matches("pattern_404/file.txt"));
        assertFalse(m.matches("pattern_404/file.log"));
    }
    @Test
    public void testGlobCase_405() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_405/*.txt", true);
        assertTrue(m.matches("pattern_405/file.txt"));
        assertFalse(m.matches("pattern_405/file.log"));
    }
    @Test
    public void testGlobCase_406() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_406/*.txt", true);
        assertTrue(m.matches("pattern_406/file.txt"));
        assertFalse(m.matches("pattern_406/file.log"));
    }
    @Test
    public void testGlobCase_407() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_407/*.txt", true);
        assertTrue(m.matches("pattern_407/file.txt"));
        assertFalse(m.matches("pattern_407/file.log"));
    }
    @Test
    public void testGlobCase_408() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_408/*.txt", true);
        assertTrue(m.matches("pattern_408/file.txt"));
        assertFalse(m.matches("pattern_408/file.log"));
    }
    @Test
    public void testGlobCase_409() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_409/*.txt", true);
        assertTrue(m.matches("pattern_409/file.txt"));
        assertFalse(m.matches("pattern_409/file.log"));
    }
    @Test
    public void testGlobCase_410() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_410/*.txt", true);
        assertTrue(m.matches("pattern_410/file.txt"));
        assertFalse(m.matches("pattern_410/file.log"));
    }
    @Test
    public void testGlobCase_411() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_411/*.txt", true);
        assertTrue(m.matches("pattern_411/file.txt"));
        assertFalse(m.matches("pattern_411/file.log"));
    }
    @Test
    public void testGlobCase_412() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_412/*.txt", true);
        assertTrue(m.matches("pattern_412/file.txt"));
        assertFalse(m.matches("pattern_412/file.log"));
    }
    @Test
    public void testGlobCase_413() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_413/*.txt", true);
        assertTrue(m.matches("pattern_413/file.txt"));
        assertFalse(m.matches("pattern_413/file.log"));
    }
    @Test
    public void testGlobCase_414() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_414/*.txt", true);
        assertTrue(m.matches("pattern_414/file.txt"));
        assertFalse(m.matches("pattern_414/file.log"));
    }
    @Test
    public void testGlobCase_415() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_415/*.txt", true);
        assertTrue(m.matches("pattern_415/file.txt"));
        assertFalse(m.matches("pattern_415/file.log"));
    }
    @Test
    public void testGlobCase_416() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_416/*.txt", true);
        assertTrue(m.matches("pattern_416/file.txt"));
        assertFalse(m.matches("pattern_416/file.log"));
    }
    @Test
    public void testGlobCase_417() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_417/*.txt", true);
        assertTrue(m.matches("pattern_417/file.txt"));
        assertFalse(m.matches("pattern_417/file.log"));
    }
    @Test
    public void testGlobCase_418() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_418/*.txt", true);
        assertTrue(m.matches("pattern_418/file.txt"));
        assertFalse(m.matches("pattern_418/file.log"));
    }
    @Test
    public void testGlobCase_419() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_419/*.txt", true);
        assertTrue(m.matches("pattern_419/file.txt"));
        assertFalse(m.matches("pattern_419/file.log"));
    }
    @Test
    public void testGlobCase_420() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_420/*.txt", true);
        assertTrue(m.matches("pattern_420/file.txt"));
        assertFalse(m.matches("pattern_420/file.log"));
    }
    @Test
    public void testGlobCase_421() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_421/*.txt", true);
        assertTrue(m.matches("pattern_421/file.txt"));
        assertFalse(m.matches("pattern_421/file.log"));
    }
    @Test
    public void testGlobCase_422() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_422/*.txt", true);
        assertTrue(m.matches("pattern_422/file.txt"));
        assertFalse(m.matches("pattern_422/file.log"));
    }
    @Test
    public void testGlobCase_423() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_423/*.txt", true);
        assertTrue(m.matches("pattern_423/file.txt"));
        assertFalse(m.matches("pattern_423/file.log"));
    }
    @Test
    public void testGlobCase_424() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_424/*.txt", true);
        assertTrue(m.matches("pattern_424/file.txt"));
        assertFalse(m.matches("pattern_424/file.log"));
    }
    @Test
    public void testGlobCase_425() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_425/*.txt", true);
        assertTrue(m.matches("pattern_425/file.txt"));
        assertFalse(m.matches("pattern_425/file.log"));
    }
    @Test
    public void testGlobCase_426() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_426/*.txt", true);
        assertTrue(m.matches("pattern_426/file.txt"));
        assertFalse(m.matches("pattern_426/file.log"));
    }
    @Test
    public void testGlobCase_427() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_427/*.txt", true);
        assertTrue(m.matches("pattern_427/file.txt"));
        assertFalse(m.matches("pattern_427/file.log"));
    }
    @Test
    public void testGlobCase_428() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_428/*.txt", true);
        assertTrue(m.matches("pattern_428/file.txt"));
        assertFalse(m.matches("pattern_428/file.log"));
    }
    @Test
    public void testGlobCase_429() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_429/*.txt", true);
        assertTrue(m.matches("pattern_429/file.txt"));
        assertFalse(m.matches("pattern_429/file.log"));
    }
    @Test
    public void testGlobCase_430() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_430/*.txt", true);
        assertTrue(m.matches("pattern_430/file.txt"));
        assertFalse(m.matches("pattern_430/file.log"));
    }
    @Test
    public void testGlobCase_431() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_431/*.txt", true);
        assertTrue(m.matches("pattern_431/file.txt"));
        assertFalse(m.matches("pattern_431/file.log"));
    }
    @Test
    public void testGlobCase_432() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_432/*.txt", true);
        assertTrue(m.matches("pattern_432/file.txt"));
        assertFalse(m.matches("pattern_432/file.log"));
    }
    @Test
    public void testGlobCase_433() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_433/*.txt", true);
        assertTrue(m.matches("pattern_433/file.txt"));
        assertFalse(m.matches("pattern_433/file.log"));
    }
    @Test
    public void testGlobCase_434() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_434/*.txt", true);
        assertTrue(m.matches("pattern_434/file.txt"));
        assertFalse(m.matches("pattern_434/file.log"));
    }
    @Test
    public void testGlobCase_435() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_435/*.txt", true);
        assertTrue(m.matches("pattern_435/file.txt"));
        assertFalse(m.matches("pattern_435/file.log"));
    }
    @Test
    public void testGlobCase_436() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_436/*.txt", true);
        assertTrue(m.matches("pattern_436/file.txt"));
        assertFalse(m.matches("pattern_436/file.log"));
    }
    @Test
    public void testGlobCase_437() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_437/*.txt", true);
        assertTrue(m.matches("pattern_437/file.txt"));
        assertFalse(m.matches("pattern_437/file.log"));
    }
    @Test
    public void testGlobCase_438() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_438/*.txt", true);
        assertTrue(m.matches("pattern_438/file.txt"));
        assertFalse(m.matches("pattern_438/file.log"));
    }
    @Test
    public void testGlobCase_439() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_439/*.txt", true);
        assertTrue(m.matches("pattern_439/file.txt"));
        assertFalse(m.matches("pattern_439/file.log"));
    }
    @Test
    public void testGlobCase_440() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_440/*.txt", true);
        assertTrue(m.matches("pattern_440/file.txt"));
        assertFalse(m.matches("pattern_440/file.log"));
    }
    @Test
    public void testGlobCase_441() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_441/*.txt", true);
        assertTrue(m.matches("pattern_441/file.txt"));
        assertFalse(m.matches("pattern_441/file.log"));
    }
    @Test
    public void testGlobCase_442() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_442/*.txt", true);
        assertTrue(m.matches("pattern_442/file.txt"));
        assertFalse(m.matches("pattern_442/file.log"));
    }
    @Test
    public void testGlobCase_443() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_443/*.txt", true);
        assertTrue(m.matches("pattern_443/file.txt"));
        assertFalse(m.matches("pattern_443/file.log"));
    }
    @Test
    public void testGlobCase_444() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_444/*.txt", true);
        assertTrue(m.matches("pattern_444/file.txt"));
        assertFalse(m.matches("pattern_444/file.log"));
    }
    @Test
    public void testGlobCase_445() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_445/*.txt", true);
        assertTrue(m.matches("pattern_445/file.txt"));
        assertFalse(m.matches("pattern_445/file.log"));
    }
    @Test
    public void testGlobCase_446() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_446/*.txt", true);
        assertTrue(m.matches("pattern_446/file.txt"));
        assertFalse(m.matches("pattern_446/file.log"));
    }
    @Test
    public void testGlobCase_447() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_447/*.txt", true);
        assertTrue(m.matches("pattern_447/file.txt"));
        assertFalse(m.matches("pattern_447/file.log"));
    }
    @Test
    public void testGlobCase_448() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_448/*.txt", true);
        assertTrue(m.matches("pattern_448/file.txt"));
        assertFalse(m.matches("pattern_448/file.log"));
    }
    @Test
    public void testGlobCase_449() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_449/*.txt", true);
        assertTrue(m.matches("pattern_449/file.txt"));
        assertFalse(m.matches("pattern_449/file.log"));
    }
    @Test
    public void testGlobCase_450() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_450/*.txt", true);
        assertTrue(m.matches("pattern_450/file.txt"));
        assertFalse(m.matches("pattern_450/file.log"));
    }
    @Test
    public void testGlobCase_451() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_451/*.txt", true);
        assertTrue(m.matches("pattern_451/file.txt"));
        assertFalse(m.matches("pattern_451/file.log"));
    }
    @Test
    public void testGlobCase_452() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_452/*.txt", true);
        assertTrue(m.matches("pattern_452/file.txt"));
        assertFalse(m.matches("pattern_452/file.log"));
    }
    @Test
    public void testGlobCase_453() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_453/*.txt", true);
        assertTrue(m.matches("pattern_453/file.txt"));
        assertFalse(m.matches("pattern_453/file.log"));
    }
    @Test
    public void testGlobCase_454() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_454/*.txt", true);
        assertTrue(m.matches("pattern_454/file.txt"));
        assertFalse(m.matches("pattern_454/file.log"));
    }
    @Test
    public void testGlobCase_455() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_455/*.txt", true);
        assertTrue(m.matches("pattern_455/file.txt"));
        assertFalse(m.matches("pattern_455/file.log"));
    }
    @Test
    public void testGlobCase_456() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_456/*.txt", true);
        assertTrue(m.matches("pattern_456/file.txt"));
        assertFalse(m.matches("pattern_456/file.log"));
    }
    @Test
    public void testGlobCase_457() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_457/*.txt", true);
        assertTrue(m.matches("pattern_457/file.txt"));
        assertFalse(m.matches("pattern_457/file.log"));
    }
    @Test
    public void testGlobCase_458() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_458/*.txt", true);
        assertTrue(m.matches("pattern_458/file.txt"));
        assertFalse(m.matches("pattern_458/file.log"));
    }
    @Test
    public void testGlobCase_459() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_459/*.txt", true);
        assertTrue(m.matches("pattern_459/file.txt"));
        assertFalse(m.matches("pattern_459/file.log"));
    }
    @Test
    public void testGlobCase_460() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_460/*.txt", true);
        assertTrue(m.matches("pattern_460/file.txt"));
        assertFalse(m.matches("pattern_460/file.log"));
    }
    @Test
    public void testGlobCase_461() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_461/*.txt", true);
        assertTrue(m.matches("pattern_461/file.txt"));
        assertFalse(m.matches("pattern_461/file.log"));
    }
    @Test
    public void testGlobCase_462() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_462/*.txt", true);
        assertTrue(m.matches("pattern_462/file.txt"));
        assertFalse(m.matches("pattern_462/file.log"));
    }
    @Test
    public void testGlobCase_463() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_463/*.txt", true);
        assertTrue(m.matches("pattern_463/file.txt"));
        assertFalse(m.matches("pattern_463/file.log"));
    }
    @Test
    public void testGlobCase_464() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_464/*.txt", true);
        assertTrue(m.matches("pattern_464/file.txt"));
        assertFalse(m.matches("pattern_464/file.log"));
    }
    @Test
    public void testGlobCase_465() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_465/*.txt", true);
        assertTrue(m.matches("pattern_465/file.txt"));
        assertFalse(m.matches("pattern_465/file.log"));
    }
    @Test
    public void testGlobCase_466() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_466/*.txt", true);
        assertTrue(m.matches("pattern_466/file.txt"));
        assertFalse(m.matches("pattern_466/file.log"));
    }
    @Test
    public void testGlobCase_467() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_467/*.txt", true);
        assertTrue(m.matches("pattern_467/file.txt"));
        assertFalse(m.matches("pattern_467/file.log"));
    }
    @Test
    public void testGlobCase_468() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_468/*.txt", true);
        assertTrue(m.matches("pattern_468/file.txt"));
        assertFalse(m.matches("pattern_468/file.log"));
    }
    @Test
    public void testGlobCase_469() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_469/*.txt", true);
        assertTrue(m.matches("pattern_469/file.txt"));
        assertFalse(m.matches("pattern_469/file.log"));
    }
    @Test
    public void testGlobCase_470() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_470/*.txt", true);
        assertTrue(m.matches("pattern_470/file.txt"));
        assertFalse(m.matches("pattern_470/file.log"));
    }
    @Test
    public void testGlobCase_471() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_471/*.txt", true);
        assertTrue(m.matches("pattern_471/file.txt"));
        assertFalse(m.matches("pattern_471/file.log"));
    }
    @Test
    public void testGlobCase_472() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_472/*.txt", true);
        assertTrue(m.matches("pattern_472/file.txt"));
        assertFalse(m.matches("pattern_472/file.log"));
    }
    @Test
    public void testGlobCase_473() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_473/*.txt", true);
        assertTrue(m.matches("pattern_473/file.txt"));
        assertFalse(m.matches("pattern_473/file.log"));
    }
    @Test
    public void testGlobCase_474() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_474/*.txt", true);
        assertTrue(m.matches("pattern_474/file.txt"));
        assertFalse(m.matches("pattern_474/file.log"));
    }
    @Test
    public void testGlobCase_475() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_475/*.txt", true);
        assertTrue(m.matches("pattern_475/file.txt"));
        assertFalse(m.matches("pattern_475/file.log"));
    }
    @Test
    public void testGlobCase_476() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_476/*.txt", true);
        assertTrue(m.matches("pattern_476/file.txt"));
        assertFalse(m.matches("pattern_476/file.log"));
    }
    @Test
    public void testGlobCase_477() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_477/*.txt", true);
        assertTrue(m.matches("pattern_477/file.txt"));
        assertFalse(m.matches("pattern_477/file.log"));
    }
    @Test
    public void testGlobCase_478() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_478/*.txt", true);
        assertTrue(m.matches("pattern_478/file.txt"));
        assertFalse(m.matches("pattern_478/file.log"));
    }
    @Test
    public void testGlobCase_479() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_479/*.txt", true);
        assertTrue(m.matches("pattern_479/file.txt"));
        assertFalse(m.matches("pattern_479/file.log"));
    }
    @Test
    public void testGlobCase_480() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_480/*.txt", true);
        assertTrue(m.matches("pattern_480/file.txt"));
        assertFalse(m.matches("pattern_480/file.log"));
    }
    @Test
    public void testGlobCase_481() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_481/*.txt", true);
        assertTrue(m.matches("pattern_481/file.txt"));
        assertFalse(m.matches("pattern_481/file.log"));
    }
    @Test
    public void testGlobCase_482() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_482/*.txt", true);
        assertTrue(m.matches("pattern_482/file.txt"));
        assertFalse(m.matches("pattern_482/file.log"));
    }
    @Test
    public void testGlobCase_483() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_483/*.txt", true);
        assertTrue(m.matches("pattern_483/file.txt"));
        assertFalse(m.matches("pattern_483/file.log"));
    }
    @Test
    public void testGlobCase_484() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_484/*.txt", true);
        assertTrue(m.matches("pattern_484/file.txt"));
        assertFalse(m.matches("pattern_484/file.log"));
    }
    @Test
    public void testGlobCase_485() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_485/*.txt", true);
        assertTrue(m.matches("pattern_485/file.txt"));
        assertFalse(m.matches("pattern_485/file.log"));
    }
    @Test
    public void testGlobCase_486() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_486/*.txt", true);
        assertTrue(m.matches("pattern_486/file.txt"));
        assertFalse(m.matches("pattern_486/file.log"));
    }
    @Test
    public void testGlobCase_487() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_487/*.txt", true);
        assertTrue(m.matches("pattern_487/file.txt"));
        assertFalse(m.matches("pattern_487/file.log"));
    }
    @Test
    public void testGlobCase_488() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_488/*.txt", true);
        assertTrue(m.matches("pattern_488/file.txt"));
        assertFalse(m.matches("pattern_488/file.log"));
    }
    @Test
    public void testGlobCase_489() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_489/*.txt", true);
        assertTrue(m.matches("pattern_489/file.txt"));
        assertFalse(m.matches("pattern_489/file.log"));
    }
    @Test
    public void testGlobCase_490() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_490/*.txt", true);
        assertTrue(m.matches("pattern_490/file.txt"));
        assertFalse(m.matches("pattern_490/file.log"));
    }
    @Test
    public void testGlobCase_491() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_491/*.txt", true);
        assertTrue(m.matches("pattern_491/file.txt"));
        assertFalse(m.matches("pattern_491/file.log"));
    }
    @Test
    public void testGlobCase_492() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_492/*.txt", true);
        assertTrue(m.matches("pattern_492/file.txt"));
        assertFalse(m.matches("pattern_492/file.log"));
    }
    @Test
    public void testGlobCase_493() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_493/*.txt", true);
        assertTrue(m.matches("pattern_493/file.txt"));
        assertFalse(m.matches("pattern_493/file.log"));
    }
    @Test
    public void testGlobCase_494() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_494/*.txt", true);
        assertTrue(m.matches("pattern_494/file.txt"));
        assertFalse(m.matches("pattern_494/file.log"));
    }
    @Test
    public void testGlobCase_495() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_495/*.txt", true);
        assertTrue(m.matches("pattern_495/file.txt"));
        assertFalse(m.matches("pattern_495/file.log"));
    }
    @Test
    public void testGlobCase_496() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_496/*.txt", true);
        assertTrue(m.matches("pattern_496/file.txt"));
        assertFalse(m.matches("pattern_496/file.log"));
    }
    @Test
    public void testGlobCase_497() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_497/*.txt", true);
        assertTrue(m.matches("pattern_497/file.txt"));
        assertFalse(m.matches("pattern_497/file.log"));
    }
    @Test
    public void testGlobCase_498() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_498/*.txt", true);
        assertTrue(m.matches("pattern_498/file.txt"));
        assertFalse(m.matches("pattern_498/file.log"));
    }
    @Test
    public void testGlobCase_499() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_499/*.txt", true);
        assertTrue(m.matches("pattern_499/file.txt"));
        assertFalse(m.matches("pattern_499/file.log"));
    }
    @Test
    public void testGlobCase_500() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_500/*.txt", true);
        assertTrue(m.matches("pattern_500/file.txt"));
        assertFalse(m.matches("pattern_500/file.log"));
    }
    @Test
    public void testGlobCase_501() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_501/*.txt", true);
        assertTrue(m.matches("pattern_501/file.txt"));
        assertFalse(m.matches("pattern_501/file.log"));
    }
    @Test
    public void testGlobCase_502() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_502/*.txt", true);
        assertTrue(m.matches("pattern_502/file.txt"));
        assertFalse(m.matches("pattern_502/file.log"));
    }
    @Test
    public void testGlobCase_503() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_503/*.txt", true);
        assertTrue(m.matches("pattern_503/file.txt"));
        assertFalse(m.matches("pattern_503/file.log"));
    }
    @Test
    public void testGlobCase_504() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_504/*.txt", true);
        assertTrue(m.matches("pattern_504/file.txt"));
        assertFalse(m.matches("pattern_504/file.log"));
    }
    @Test
    public void testGlobCase_505() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_505/*.txt", true);
        assertTrue(m.matches("pattern_505/file.txt"));
        assertFalse(m.matches("pattern_505/file.log"));
    }
    @Test
    public void testGlobCase_506() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_506/*.txt", true);
        assertTrue(m.matches("pattern_506/file.txt"));
        assertFalse(m.matches("pattern_506/file.log"));
    }
    @Test
    public void testGlobCase_507() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_507/*.txt", true);
        assertTrue(m.matches("pattern_507/file.txt"));
        assertFalse(m.matches("pattern_507/file.log"));
    }
    @Test
    public void testGlobCase_508() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_508/*.txt", true);
        assertTrue(m.matches("pattern_508/file.txt"));
        assertFalse(m.matches("pattern_508/file.log"));
    }
    @Test
    public void testGlobCase_509() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_509/*.txt", true);
        assertTrue(m.matches("pattern_509/file.txt"));
        assertFalse(m.matches("pattern_509/file.log"));
    }
    @Test
    public void testGlobCase_510() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_510/*.txt", true);
        assertTrue(m.matches("pattern_510/file.txt"));
        assertFalse(m.matches("pattern_510/file.log"));
    }
    @Test
    public void testGlobCase_511() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_511/*.txt", true);
        assertTrue(m.matches("pattern_511/file.txt"));
        assertFalse(m.matches("pattern_511/file.log"));
    }
    @Test
    public void testGlobCase_512() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_512/*.txt", true);
        assertTrue(m.matches("pattern_512/file.txt"));
        assertFalse(m.matches("pattern_512/file.log"));
    }
    @Test
    public void testGlobCase_513() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_513/*.txt", true);
        assertTrue(m.matches("pattern_513/file.txt"));
        assertFalse(m.matches("pattern_513/file.log"));
    }
    @Test
    public void testGlobCase_514() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_514/*.txt", true);
        assertTrue(m.matches("pattern_514/file.txt"));
        assertFalse(m.matches("pattern_514/file.log"));
    }
    @Test
    public void testGlobCase_515() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_515/*.txt", true);
        assertTrue(m.matches("pattern_515/file.txt"));
        assertFalse(m.matches("pattern_515/file.log"));
    }
    @Test
    public void testGlobCase_516() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_516/*.txt", true);
        assertTrue(m.matches("pattern_516/file.txt"));
        assertFalse(m.matches("pattern_516/file.log"));
    }
    @Test
    public void testGlobCase_517() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_517/*.txt", true);
        assertTrue(m.matches("pattern_517/file.txt"));
        assertFalse(m.matches("pattern_517/file.log"));
    }
    @Test
    public void testGlobCase_518() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_518/*.txt", true);
        assertTrue(m.matches("pattern_518/file.txt"));
        assertFalse(m.matches("pattern_518/file.log"));
    }
    @Test
    public void testGlobCase_519() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_519/*.txt", true);
        assertTrue(m.matches("pattern_519/file.txt"));
        assertFalse(m.matches("pattern_519/file.log"));
    }
    @Test
    public void testGlobCase_520() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_520/*.txt", true);
        assertTrue(m.matches("pattern_520/file.txt"));
        assertFalse(m.matches("pattern_520/file.log"));
    }
    @Test
    public void testGlobCase_521() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_521/*.txt", true);
        assertTrue(m.matches("pattern_521/file.txt"));
        assertFalse(m.matches("pattern_521/file.log"));
    }
    @Test
    public void testGlobCase_522() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_522/*.txt", true);
        assertTrue(m.matches("pattern_522/file.txt"));
        assertFalse(m.matches("pattern_522/file.log"));
    }
    @Test
    public void testGlobCase_523() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_523/*.txt", true);
        assertTrue(m.matches("pattern_523/file.txt"));
        assertFalse(m.matches("pattern_523/file.log"));
    }
    @Test
    public void testGlobCase_524() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_524/*.txt", true);
        assertTrue(m.matches("pattern_524/file.txt"));
        assertFalse(m.matches("pattern_524/file.log"));
    }
    @Test
    public void testGlobCase_525() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_525/*.txt", true);
        assertTrue(m.matches("pattern_525/file.txt"));
        assertFalse(m.matches("pattern_525/file.log"));
    }
    @Test
    public void testGlobCase_526() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_526/*.txt", true);
        assertTrue(m.matches("pattern_526/file.txt"));
        assertFalse(m.matches("pattern_526/file.log"));
    }
    @Test
    public void testGlobCase_527() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_527/*.txt", true);
        assertTrue(m.matches("pattern_527/file.txt"));
        assertFalse(m.matches("pattern_527/file.log"));
    }
    @Test
    public void testGlobCase_528() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_528/*.txt", true);
        assertTrue(m.matches("pattern_528/file.txt"));
        assertFalse(m.matches("pattern_528/file.log"));
    }
    @Test
    public void testGlobCase_529() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_529/*.txt", true);
        assertTrue(m.matches("pattern_529/file.txt"));
        assertFalse(m.matches("pattern_529/file.log"));
    }
    @Test
    public void testGlobCase_530() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_530/*.txt", true);
        assertTrue(m.matches("pattern_530/file.txt"));
        assertFalse(m.matches("pattern_530/file.log"));
    }
    @Test
    public void testGlobCase_531() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_531/*.txt", true);
        assertTrue(m.matches("pattern_531/file.txt"));
        assertFalse(m.matches("pattern_531/file.log"));
    }
    @Test
    public void testGlobCase_532() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_532/*.txt", true);
        assertTrue(m.matches("pattern_532/file.txt"));
        assertFalse(m.matches("pattern_532/file.log"));
    }
    @Test
    public void testGlobCase_533() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_533/*.txt", true);
        assertTrue(m.matches("pattern_533/file.txt"));
        assertFalse(m.matches("pattern_533/file.log"));
    }
    @Test
    public void testGlobCase_534() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_534/*.txt", true);
        assertTrue(m.matches("pattern_534/file.txt"));
        assertFalse(m.matches("pattern_534/file.log"));
    }
    @Test
    public void testGlobCase_535() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_535/*.txt", true);
        assertTrue(m.matches("pattern_535/file.txt"));
        assertFalse(m.matches("pattern_535/file.log"));
    }
    @Test
    public void testGlobCase_536() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_536/*.txt", true);
        assertTrue(m.matches("pattern_536/file.txt"));
        assertFalse(m.matches("pattern_536/file.log"));
    }
    @Test
    public void testGlobCase_537() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_537/*.txt", true);
        assertTrue(m.matches("pattern_537/file.txt"));
        assertFalse(m.matches("pattern_537/file.log"));
    }
    @Test
    public void testGlobCase_538() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_538/*.txt", true);
        assertTrue(m.matches("pattern_538/file.txt"));
        assertFalse(m.matches("pattern_538/file.log"));
    }
    @Test
    public void testGlobCase_539() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_539/*.txt", true);
        assertTrue(m.matches("pattern_539/file.txt"));
        assertFalse(m.matches("pattern_539/file.log"));
    }
    @Test
    public void testGlobCase_540() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_540/*.txt", true);
        assertTrue(m.matches("pattern_540/file.txt"));
        assertFalse(m.matches("pattern_540/file.log"));
    }
    @Test
    public void testGlobCase_541() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_541/*.txt", true);
        assertTrue(m.matches("pattern_541/file.txt"));
        assertFalse(m.matches("pattern_541/file.log"));
    }
    @Test
    public void testGlobCase_542() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_542/*.txt", true);
        assertTrue(m.matches("pattern_542/file.txt"));
        assertFalse(m.matches("pattern_542/file.log"));
    }
    @Test
    public void testGlobCase_543() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_543/*.txt", true);
        assertTrue(m.matches("pattern_543/file.txt"));
        assertFalse(m.matches("pattern_543/file.log"));
    }
    @Test
    public void testGlobCase_544() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_544/*.txt", true);
        assertTrue(m.matches("pattern_544/file.txt"));
        assertFalse(m.matches("pattern_544/file.log"));
    }
    @Test
    public void testGlobCase_545() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_545/*.txt", true);
        assertTrue(m.matches("pattern_545/file.txt"));
        assertFalse(m.matches("pattern_545/file.log"));
    }
    @Test
    public void testGlobCase_546() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_546/*.txt", true);
        assertTrue(m.matches("pattern_546/file.txt"));
        assertFalse(m.matches("pattern_546/file.log"));
    }
    @Test
    public void testGlobCase_547() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_547/*.txt", true);
        assertTrue(m.matches("pattern_547/file.txt"));
        assertFalse(m.matches("pattern_547/file.log"));
    }
    @Test
    public void testGlobCase_548() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_548/*.txt", true);
        assertTrue(m.matches("pattern_548/file.txt"));
        assertFalse(m.matches("pattern_548/file.log"));
    }
    @Test
    public void testGlobCase_549() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_549/*.txt", true);
        assertTrue(m.matches("pattern_549/file.txt"));
        assertFalse(m.matches("pattern_549/file.log"));
    }
    @Test
    public void testGlobCase_550() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_550/*.txt", true);
        assertTrue(m.matches("pattern_550/file.txt"));
        assertFalse(m.matches("pattern_550/file.log"));
    }
    @Test
    public void testGlobCase_551() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_551/*.txt", true);
        assertTrue(m.matches("pattern_551/file.txt"));
        assertFalse(m.matches("pattern_551/file.log"));
    }
    @Test
    public void testGlobCase_552() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_552/*.txt", true);
        assertTrue(m.matches("pattern_552/file.txt"));
        assertFalse(m.matches("pattern_552/file.log"));
    }
    @Test
    public void testGlobCase_553() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_553/*.txt", true);
        assertTrue(m.matches("pattern_553/file.txt"));
        assertFalse(m.matches("pattern_553/file.log"));
    }
    @Test
    public void testGlobCase_554() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_554/*.txt", true);
        assertTrue(m.matches("pattern_554/file.txt"));
        assertFalse(m.matches("pattern_554/file.log"));
    }
    @Test
    public void testGlobCase_555() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_555/*.txt", true);
        assertTrue(m.matches("pattern_555/file.txt"));
        assertFalse(m.matches("pattern_555/file.log"));
    }
    @Test
    public void testGlobCase_556() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_556/*.txt", true);
        assertTrue(m.matches("pattern_556/file.txt"));
        assertFalse(m.matches("pattern_556/file.log"));
    }
    @Test
    public void testGlobCase_557() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_557/*.txt", true);
        assertTrue(m.matches("pattern_557/file.txt"));
        assertFalse(m.matches("pattern_557/file.log"));
    }
    @Test
    public void testGlobCase_558() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_558/*.txt", true);
        assertTrue(m.matches("pattern_558/file.txt"));
        assertFalse(m.matches("pattern_558/file.log"));
    }
    @Test
    public void testGlobCase_559() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_559/*.txt", true);
        assertTrue(m.matches("pattern_559/file.txt"));
        assertFalse(m.matches("pattern_559/file.log"));
    }
    @Test
    public void testGlobCase_560() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_560/*.txt", true);
        assertTrue(m.matches("pattern_560/file.txt"));
        assertFalse(m.matches("pattern_560/file.log"));
    }
    @Test
    public void testGlobCase_561() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_561/*.txt", true);
        assertTrue(m.matches("pattern_561/file.txt"));
        assertFalse(m.matches("pattern_561/file.log"));
    }
    @Test
    public void testGlobCase_562() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_562/*.txt", true);
        assertTrue(m.matches("pattern_562/file.txt"));
        assertFalse(m.matches("pattern_562/file.log"));
    }
    @Test
    public void testGlobCase_563() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_563/*.txt", true);
        assertTrue(m.matches("pattern_563/file.txt"));
        assertFalse(m.matches("pattern_563/file.log"));
    }
    @Test
    public void testGlobCase_564() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_564/*.txt", true);
        assertTrue(m.matches("pattern_564/file.txt"));
        assertFalse(m.matches("pattern_564/file.log"));
    }
    @Test
    public void testGlobCase_565() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_565/*.txt", true);
        assertTrue(m.matches("pattern_565/file.txt"));
        assertFalse(m.matches("pattern_565/file.log"));
    }
    @Test
    public void testGlobCase_566() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_566/*.txt", true);
        assertTrue(m.matches("pattern_566/file.txt"));
        assertFalse(m.matches("pattern_566/file.log"));
    }
    @Test
    public void testGlobCase_567() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_567/*.txt", true);
        assertTrue(m.matches("pattern_567/file.txt"));
        assertFalse(m.matches("pattern_567/file.log"));
    }
    @Test
    public void testGlobCase_568() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_568/*.txt", true);
        assertTrue(m.matches("pattern_568/file.txt"));
        assertFalse(m.matches("pattern_568/file.log"));
    }
    @Test
    public void testGlobCase_569() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_569/*.txt", true);
        assertTrue(m.matches("pattern_569/file.txt"));
        assertFalse(m.matches("pattern_569/file.log"));
    }
    @Test
    public void testGlobCase_570() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_570/*.txt", true);
        assertTrue(m.matches("pattern_570/file.txt"));
        assertFalse(m.matches("pattern_570/file.log"));
    }
    @Test
    public void testGlobCase_571() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_571/*.txt", true);
        assertTrue(m.matches("pattern_571/file.txt"));
        assertFalse(m.matches("pattern_571/file.log"));
    }
    @Test
    public void testGlobCase_572() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_572/*.txt", true);
        assertTrue(m.matches("pattern_572/file.txt"));
        assertFalse(m.matches("pattern_572/file.log"));
    }
    @Test
    public void testGlobCase_573() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_573/*.txt", true);
        assertTrue(m.matches("pattern_573/file.txt"));
        assertFalse(m.matches("pattern_573/file.log"));
    }
    @Test
    public void testGlobCase_574() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_574/*.txt", true);
        assertTrue(m.matches("pattern_574/file.txt"));
        assertFalse(m.matches("pattern_574/file.log"));
    }
    @Test
    public void testGlobCase_575() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_575/*.txt", true);
        assertTrue(m.matches("pattern_575/file.txt"));
        assertFalse(m.matches("pattern_575/file.log"));
    }
    @Test
    public void testGlobCase_576() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_576/*.txt", true);
        assertTrue(m.matches("pattern_576/file.txt"));
        assertFalse(m.matches("pattern_576/file.log"));
    }
    @Test
    public void testGlobCase_577() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_577/*.txt", true);
        assertTrue(m.matches("pattern_577/file.txt"));
        assertFalse(m.matches("pattern_577/file.log"));
    }
    @Test
    public void testGlobCase_578() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_578/*.txt", true);
        assertTrue(m.matches("pattern_578/file.txt"));
        assertFalse(m.matches("pattern_578/file.log"));
    }
    @Test
    public void testGlobCase_579() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_579/*.txt", true);
        assertTrue(m.matches("pattern_579/file.txt"));
        assertFalse(m.matches("pattern_579/file.log"));
    }
    @Test
    public void testGlobCase_580() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_580/*.txt", true);
        assertTrue(m.matches("pattern_580/file.txt"));
        assertFalse(m.matches("pattern_580/file.log"));
    }
    @Test
    public void testGlobCase_581() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_581/*.txt", true);
        assertTrue(m.matches("pattern_581/file.txt"));
        assertFalse(m.matches("pattern_581/file.log"));
    }
    @Test
    public void testGlobCase_582() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_582/*.txt", true);
        assertTrue(m.matches("pattern_582/file.txt"));
        assertFalse(m.matches("pattern_582/file.log"));
    }
    @Test
    public void testGlobCase_583() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_583/*.txt", true);
        assertTrue(m.matches("pattern_583/file.txt"));
        assertFalse(m.matches("pattern_583/file.log"));
    }
    @Test
    public void testGlobCase_584() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_584/*.txt", true);
        assertTrue(m.matches("pattern_584/file.txt"));
        assertFalse(m.matches("pattern_584/file.log"));
    }
    @Test
    public void testGlobCase_585() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_585/*.txt", true);
        assertTrue(m.matches("pattern_585/file.txt"));
        assertFalse(m.matches("pattern_585/file.log"));
    }
    @Test
    public void testGlobCase_586() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_586/*.txt", true);
        assertTrue(m.matches("pattern_586/file.txt"));
        assertFalse(m.matches("pattern_586/file.log"));
    }
    @Test
    public void testGlobCase_587() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_587/*.txt", true);
        assertTrue(m.matches("pattern_587/file.txt"));
        assertFalse(m.matches("pattern_587/file.log"));
    }
    @Test
    public void testGlobCase_588() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_588/*.txt", true);
        assertTrue(m.matches("pattern_588/file.txt"));
        assertFalse(m.matches("pattern_588/file.log"));
    }
    @Test
    public void testGlobCase_589() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_589/*.txt", true);
        assertTrue(m.matches("pattern_589/file.txt"));
        assertFalse(m.matches("pattern_589/file.log"));
    }
    @Test
    public void testGlobCase_590() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_590/*.txt", true);
        assertTrue(m.matches("pattern_590/file.txt"));
        assertFalse(m.matches("pattern_590/file.log"));
    }
    @Test
    public void testGlobCase_591() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_591/*.txt", true);
        assertTrue(m.matches("pattern_591/file.txt"));
        assertFalse(m.matches("pattern_591/file.log"));
    }
    @Test
    public void testGlobCase_592() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_592/*.txt", true);
        assertTrue(m.matches("pattern_592/file.txt"));
        assertFalse(m.matches("pattern_592/file.log"));
    }
    @Test
    public void testGlobCase_593() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_593/*.txt", true);
        assertTrue(m.matches("pattern_593/file.txt"));
        assertFalse(m.matches("pattern_593/file.log"));
    }
    @Test
    public void testGlobCase_594() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_594/*.txt", true);
        assertTrue(m.matches("pattern_594/file.txt"));
        assertFalse(m.matches("pattern_594/file.log"));
    }
    @Test
    public void testGlobCase_595() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_595/*.txt", true);
        assertTrue(m.matches("pattern_595/file.txt"));
        assertFalse(m.matches("pattern_595/file.log"));
    }
    @Test
    public void testGlobCase_596() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_596/*.txt", true);
        assertTrue(m.matches("pattern_596/file.txt"));
        assertFalse(m.matches("pattern_596/file.log"));
    }
    @Test
    public void testGlobCase_597() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_597/*.txt", true);
        assertTrue(m.matches("pattern_597/file.txt"));
        assertFalse(m.matches("pattern_597/file.log"));
    }
    @Test
    public void testGlobCase_598() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_598/*.txt", true);
        assertTrue(m.matches("pattern_598/file.txt"));
        assertFalse(m.matches("pattern_598/file.log"));
    }
    @Test
    public void testGlobCase_599() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_599/*.txt", true);
        assertTrue(m.matches("pattern_599/file.txt"));
        assertFalse(m.matches("pattern_599/file.log"));
    }
    @Test
    public void testGlobCase_600() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_600/*.txt", true);
        assertTrue(m.matches("pattern_600/file.txt"));
        assertFalse(m.matches("pattern_600/file.log"));
    }
    @Test
    public void testGlobCase_601() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_601/*.txt", true);
        assertTrue(m.matches("pattern_601/file.txt"));
        assertFalse(m.matches("pattern_601/file.log"));
    }
    @Test
    public void testGlobCase_602() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_602/*.txt", true);
        assertTrue(m.matches("pattern_602/file.txt"));
        assertFalse(m.matches("pattern_602/file.log"));
    }
    @Test
    public void testGlobCase_603() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_603/*.txt", true);
        assertTrue(m.matches("pattern_603/file.txt"));
        assertFalse(m.matches("pattern_603/file.log"));
    }
    @Test
    public void testGlobCase_604() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_604/*.txt", true);
        assertTrue(m.matches("pattern_604/file.txt"));
        assertFalse(m.matches("pattern_604/file.log"));
    }
    @Test
    public void testGlobCase_605() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_605/*.txt", true);
        assertTrue(m.matches("pattern_605/file.txt"));
        assertFalse(m.matches("pattern_605/file.log"));
    }
    @Test
    public void testGlobCase_606() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_606/*.txt", true);
        assertTrue(m.matches("pattern_606/file.txt"));
        assertFalse(m.matches("pattern_606/file.log"));
    }
    @Test
    public void testGlobCase_607() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_607/*.txt", true);
        assertTrue(m.matches("pattern_607/file.txt"));
        assertFalse(m.matches("pattern_607/file.log"));
    }
    @Test
    public void testGlobCase_608() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_608/*.txt", true);
        assertTrue(m.matches("pattern_608/file.txt"));
        assertFalse(m.matches("pattern_608/file.log"));
    }
    @Test
    public void testGlobCase_609() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_609/*.txt", true);
        assertTrue(m.matches("pattern_609/file.txt"));
        assertFalse(m.matches("pattern_609/file.log"));
    }
    @Test
    public void testGlobCase_610() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_610/*.txt", true);
        assertTrue(m.matches("pattern_610/file.txt"));
        assertFalse(m.matches("pattern_610/file.log"));
    }
    @Test
    public void testGlobCase_611() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_611/*.txt", true);
        assertTrue(m.matches("pattern_611/file.txt"));
        assertFalse(m.matches("pattern_611/file.log"));
    }
    @Test
    public void testGlobCase_612() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_612/*.txt", true);
        assertTrue(m.matches("pattern_612/file.txt"));
        assertFalse(m.matches("pattern_612/file.log"));
    }
    @Test
    public void testGlobCase_613() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_613/*.txt", true);
        assertTrue(m.matches("pattern_613/file.txt"));
        assertFalse(m.matches("pattern_613/file.log"));
    }
    @Test
    public void testGlobCase_614() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_614/*.txt", true);
        assertTrue(m.matches("pattern_614/file.txt"));
        assertFalse(m.matches("pattern_614/file.log"));
    }
    @Test
    public void testGlobCase_615() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_615/*.txt", true);
        assertTrue(m.matches("pattern_615/file.txt"));
        assertFalse(m.matches("pattern_615/file.log"));
    }
    @Test
    public void testGlobCase_616() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_616/*.txt", true);
        assertTrue(m.matches("pattern_616/file.txt"));
        assertFalse(m.matches("pattern_616/file.log"));
    }
    @Test
    public void testGlobCase_617() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_617/*.txt", true);
        assertTrue(m.matches("pattern_617/file.txt"));
        assertFalse(m.matches("pattern_617/file.log"));
    }
    @Test
    public void testGlobCase_618() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_618/*.txt", true);
        assertTrue(m.matches("pattern_618/file.txt"));
        assertFalse(m.matches("pattern_618/file.log"));
    }
    @Test
    public void testGlobCase_619() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_619/*.txt", true);
        assertTrue(m.matches("pattern_619/file.txt"));
        assertFalse(m.matches("pattern_619/file.log"));
    }
    @Test
    public void testGlobCase_620() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_620/*.txt", true);
        assertTrue(m.matches("pattern_620/file.txt"));
        assertFalse(m.matches("pattern_620/file.log"));
    }
    @Test
    public void testGlobCase_621() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_621/*.txt", true);
        assertTrue(m.matches("pattern_621/file.txt"));
        assertFalse(m.matches("pattern_621/file.log"));
    }
    @Test
    public void testGlobCase_622() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_622/*.txt", true);
        assertTrue(m.matches("pattern_622/file.txt"));
        assertFalse(m.matches("pattern_622/file.log"));
    }
    @Test
    public void testGlobCase_623() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_623/*.txt", true);
        assertTrue(m.matches("pattern_623/file.txt"));
        assertFalse(m.matches("pattern_623/file.log"));
    }
    @Test
    public void testGlobCase_624() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_624/*.txt", true);
        assertTrue(m.matches("pattern_624/file.txt"));
        assertFalse(m.matches("pattern_624/file.log"));
    }
    @Test
    public void testGlobCase_625() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_625/*.txt", true);
        assertTrue(m.matches("pattern_625/file.txt"));
        assertFalse(m.matches("pattern_625/file.log"));
    }
    @Test
    public void testGlobCase_626() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_626/*.txt", true);
        assertTrue(m.matches("pattern_626/file.txt"));
        assertFalse(m.matches("pattern_626/file.log"));
    }
    @Test
    public void testGlobCase_627() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_627/*.txt", true);
        assertTrue(m.matches("pattern_627/file.txt"));
        assertFalse(m.matches("pattern_627/file.log"));
    }
    @Test
    public void testGlobCase_628() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_628/*.txt", true);
        assertTrue(m.matches("pattern_628/file.txt"));
        assertFalse(m.matches("pattern_628/file.log"));
    }
    @Test
    public void testGlobCase_629() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_629/*.txt", true);
        assertTrue(m.matches("pattern_629/file.txt"));
        assertFalse(m.matches("pattern_629/file.log"));
    }
    @Test
    public void testGlobCase_630() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_630/*.txt", true);
        assertTrue(m.matches("pattern_630/file.txt"));
        assertFalse(m.matches("pattern_630/file.log"));
    }
    @Test
    public void testGlobCase_631() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_631/*.txt", true);
        assertTrue(m.matches("pattern_631/file.txt"));
        assertFalse(m.matches("pattern_631/file.log"));
    }
    @Test
    public void testGlobCase_632() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_632/*.txt", true);
        assertTrue(m.matches("pattern_632/file.txt"));
        assertFalse(m.matches("pattern_632/file.log"));
    }
    @Test
    public void testGlobCase_633() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_633/*.txt", true);
        assertTrue(m.matches("pattern_633/file.txt"));
        assertFalse(m.matches("pattern_633/file.log"));
    }
    @Test
    public void testGlobCase_634() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_634/*.txt", true);
        assertTrue(m.matches("pattern_634/file.txt"));
        assertFalse(m.matches("pattern_634/file.log"));
    }
    @Test
    public void testGlobCase_635() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_635/*.txt", true);
        assertTrue(m.matches("pattern_635/file.txt"));
        assertFalse(m.matches("pattern_635/file.log"));
    }
    @Test
    public void testGlobCase_636() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_636/*.txt", true);
        assertTrue(m.matches("pattern_636/file.txt"));
        assertFalse(m.matches("pattern_636/file.log"));
    }
    @Test
    public void testGlobCase_637() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_637/*.txt", true);
        assertTrue(m.matches("pattern_637/file.txt"));
        assertFalse(m.matches("pattern_637/file.log"));
    }
    @Test
    public void testGlobCase_638() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_638/*.txt", true);
        assertTrue(m.matches("pattern_638/file.txt"));
        assertFalse(m.matches("pattern_638/file.log"));
    }
    @Test
    public void testGlobCase_639() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_639/*.txt", true);
        assertTrue(m.matches("pattern_639/file.txt"));
        assertFalse(m.matches("pattern_639/file.log"));
    }
    @Test
    public void testGlobCase_640() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_640/*.txt", true);
        assertTrue(m.matches("pattern_640/file.txt"));
        assertFalse(m.matches("pattern_640/file.log"));
    }
    @Test
    public void testGlobCase_641() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_641/*.txt", true);
        assertTrue(m.matches("pattern_641/file.txt"));
        assertFalse(m.matches("pattern_641/file.log"));
    }
    @Test
    public void testGlobCase_642() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_642/*.txt", true);
        assertTrue(m.matches("pattern_642/file.txt"));
        assertFalse(m.matches("pattern_642/file.log"));
    }
    @Test
    public void testGlobCase_643() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_643/*.txt", true);
        assertTrue(m.matches("pattern_643/file.txt"));
        assertFalse(m.matches("pattern_643/file.log"));
    }
    @Test
    public void testGlobCase_644() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_644/*.txt", true);
        assertTrue(m.matches("pattern_644/file.txt"));
        assertFalse(m.matches("pattern_644/file.log"));
    }
    @Test
    public void testGlobCase_645() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_645/*.txt", true);
        assertTrue(m.matches("pattern_645/file.txt"));
        assertFalse(m.matches("pattern_645/file.log"));
    }
    @Test
    public void testGlobCase_646() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_646/*.txt", true);
        assertTrue(m.matches("pattern_646/file.txt"));
        assertFalse(m.matches("pattern_646/file.log"));
    }
    @Test
    public void testGlobCase_647() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_647/*.txt", true);
        assertTrue(m.matches("pattern_647/file.txt"));
        assertFalse(m.matches("pattern_647/file.log"));
    }
    @Test
    public void testGlobCase_648() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_648/*.txt", true);
        assertTrue(m.matches("pattern_648/file.txt"));
        assertFalse(m.matches("pattern_648/file.log"));
    }
    @Test
    public void testGlobCase_649() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_649/*.txt", true);
        assertTrue(m.matches("pattern_649/file.txt"));
        assertFalse(m.matches("pattern_649/file.log"));
    }
    @Test
    public void testGlobCase_650() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_650/*.txt", true);
        assertTrue(m.matches("pattern_650/file.txt"));
        assertFalse(m.matches("pattern_650/file.log"));
    }
    @Test
    public void testGlobCase_651() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_651/*.txt", true);
        assertTrue(m.matches("pattern_651/file.txt"));
        assertFalse(m.matches("pattern_651/file.log"));
    }
    @Test
    public void testGlobCase_652() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_652/*.txt", true);
        assertTrue(m.matches("pattern_652/file.txt"));
        assertFalse(m.matches("pattern_652/file.log"));
    }
    @Test
    public void testGlobCase_653() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_653/*.txt", true);
        assertTrue(m.matches("pattern_653/file.txt"));
        assertFalse(m.matches("pattern_653/file.log"));
    }
    @Test
    public void testGlobCase_654() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_654/*.txt", true);
        assertTrue(m.matches("pattern_654/file.txt"));
        assertFalse(m.matches("pattern_654/file.log"));
    }
    @Test
    public void testGlobCase_655() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_655/*.txt", true);
        assertTrue(m.matches("pattern_655/file.txt"));
        assertFalse(m.matches("pattern_655/file.log"));
    }
    @Test
    public void testGlobCase_656() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_656/*.txt", true);
        assertTrue(m.matches("pattern_656/file.txt"));
        assertFalse(m.matches("pattern_656/file.log"));
    }
    @Test
    public void testGlobCase_657() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_657/*.txt", true);
        assertTrue(m.matches("pattern_657/file.txt"));
        assertFalse(m.matches("pattern_657/file.log"));
    }
    @Test
    public void testGlobCase_658() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_658/*.txt", true);
        assertTrue(m.matches("pattern_658/file.txt"));
        assertFalse(m.matches("pattern_658/file.log"));
    }
    @Test
    public void testGlobCase_659() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_659/*.txt", true);
        assertTrue(m.matches("pattern_659/file.txt"));
        assertFalse(m.matches("pattern_659/file.log"));
    }
    @Test
    public void testGlobCase_660() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_660/*.txt", true);
        assertTrue(m.matches("pattern_660/file.txt"));
        assertFalse(m.matches("pattern_660/file.log"));
    }
    @Test
    public void testGlobCase_661() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_661/*.txt", true);
        assertTrue(m.matches("pattern_661/file.txt"));
        assertFalse(m.matches("pattern_661/file.log"));
    }
    @Test
    public void testGlobCase_662() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_662/*.txt", true);
        assertTrue(m.matches("pattern_662/file.txt"));
        assertFalse(m.matches("pattern_662/file.log"));
    }
    @Test
    public void testGlobCase_663() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_663/*.txt", true);
        assertTrue(m.matches("pattern_663/file.txt"));
        assertFalse(m.matches("pattern_663/file.log"));
    }
    @Test
    public void testGlobCase_664() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_664/*.txt", true);
        assertTrue(m.matches("pattern_664/file.txt"));
        assertFalse(m.matches("pattern_664/file.log"));
    }
    @Test
    public void testGlobCase_665() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_665/*.txt", true);
        assertTrue(m.matches("pattern_665/file.txt"));
        assertFalse(m.matches("pattern_665/file.log"));
    }
    @Test
    public void testGlobCase_666() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_666/*.txt", true);
        assertTrue(m.matches("pattern_666/file.txt"));
        assertFalse(m.matches("pattern_666/file.log"));
    }
    @Test
    public void testGlobCase_667() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_667/*.txt", true);
        assertTrue(m.matches("pattern_667/file.txt"));
        assertFalse(m.matches("pattern_667/file.log"));
    }
    @Test
    public void testGlobCase_668() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_668/*.txt", true);
        assertTrue(m.matches("pattern_668/file.txt"));
        assertFalse(m.matches("pattern_668/file.log"));
    }
    @Test
    public void testGlobCase_669() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_669/*.txt", true);
        assertTrue(m.matches("pattern_669/file.txt"));
        assertFalse(m.matches("pattern_669/file.log"));
    }
    @Test
    public void testGlobCase_670() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_670/*.txt", true);
        assertTrue(m.matches("pattern_670/file.txt"));
        assertFalse(m.matches("pattern_670/file.log"));
    }
    @Test
    public void testGlobCase_671() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_671/*.txt", true);
        assertTrue(m.matches("pattern_671/file.txt"));
        assertFalse(m.matches("pattern_671/file.log"));
    }
    @Test
    public void testGlobCase_672() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_672/*.txt", true);
        assertTrue(m.matches("pattern_672/file.txt"));
        assertFalse(m.matches("pattern_672/file.log"));
    }
    @Test
    public void testGlobCase_673() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_673/*.txt", true);
        assertTrue(m.matches("pattern_673/file.txt"));
        assertFalse(m.matches("pattern_673/file.log"));
    }
    @Test
    public void testGlobCase_674() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_674/*.txt", true);
        assertTrue(m.matches("pattern_674/file.txt"));
        assertFalse(m.matches("pattern_674/file.log"));
    }
    @Test
    public void testGlobCase_675() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_675/*.txt", true);
        assertTrue(m.matches("pattern_675/file.txt"));
        assertFalse(m.matches("pattern_675/file.log"));
    }
    @Test
    public void testGlobCase_676() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_676/*.txt", true);
        assertTrue(m.matches("pattern_676/file.txt"));
        assertFalse(m.matches("pattern_676/file.log"));
    }
    @Test
    public void testGlobCase_677() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_677/*.txt", true);
        assertTrue(m.matches("pattern_677/file.txt"));
        assertFalse(m.matches("pattern_677/file.log"));
    }
    @Test
    public void testGlobCase_678() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_678/*.txt", true);
        assertTrue(m.matches("pattern_678/file.txt"));
        assertFalse(m.matches("pattern_678/file.log"));
    }
    @Test
    public void testGlobCase_679() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_679/*.txt", true);
        assertTrue(m.matches("pattern_679/file.txt"));
        assertFalse(m.matches("pattern_679/file.log"));
    }
    @Test
    public void testGlobCase_680() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_680/*.txt", true);
        assertTrue(m.matches("pattern_680/file.txt"));
        assertFalse(m.matches("pattern_680/file.log"));
    }
    @Test
    public void testGlobCase_681() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_681/*.txt", true);
        assertTrue(m.matches("pattern_681/file.txt"));
        assertFalse(m.matches("pattern_681/file.log"));
    }
    @Test
    public void testGlobCase_682() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_682/*.txt", true);
        assertTrue(m.matches("pattern_682/file.txt"));
        assertFalse(m.matches("pattern_682/file.log"));
    }
    @Test
    public void testGlobCase_683() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_683/*.txt", true);
        assertTrue(m.matches("pattern_683/file.txt"));
        assertFalse(m.matches("pattern_683/file.log"));
    }
    @Test
    public void testGlobCase_684() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_684/*.txt", true);
        assertTrue(m.matches("pattern_684/file.txt"));
        assertFalse(m.matches("pattern_684/file.log"));
    }
    @Test
    public void testGlobCase_685() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_685/*.txt", true);
        assertTrue(m.matches("pattern_685/file.txt"));
        assertFalse(m.matches("pattern_685/file.log"));
    }
    @Test
    public void testGlobCase_686() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_686/*.txt", true);
        assertTrue(m.matches("pattern_686/file.txt"));
        assertFalse(m.matches("pattern_686/file.log"));
    }
    @Test
    public void testGlobCase_687() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_687/*.txt", true);
        assertTrue(m.matches("pattern_687/file.txt"));
        assertFalse(m.matches("pattern_687/file.log"));
    }
    @Test
    public void testGlobCase_688() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_688/*.txt", true);
        assertTrue(m.matches("pattern_688/file.txt"));
        assertFalse(m.matches("pattern_688/file.log"));
    }
    @Test
    public void testGlobCase_689() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_689/*.txt", true);
        assertTrue(m.matches("pattern_689/file.txt"));
        assertFalse(m.matches("pattern_689/file.log"));
    }
    @Test
    public void testGlobCase_690() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_690/*.txt", true);
        assertTrue(m.matches("pattern_690/file.txt"));
        assertFalse(m.matches("pattern_690/file.log"));
    }
    @Test
    public void testGlobCase_691() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_691/*.txt", true);
        assertTrue(m.matches("pattern_691/file.txt"));
        assertFalse(m.matches("pattern_691/file.log"));
    }
    @Test
    public void testGlobCase_692() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_692/*.txt", true);
        assertTrue(m.matches("pattern_692/file.txt"));
        assertFalse(m.matches("pattern_692/file.log"));
    }
    @Test
    public void testGlobCase_693() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_693/*.txt", true);
        assertTrue(m.matches("pattern_693/file.txt"));
        assertFalse(m.matches("pattern_693/file.log"));
    }
    @Test
    public void testGlobCase_694() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_694/*.txt", true);
        assertTrue(m.matches("pattern_694/file.txt"));
        assertFalse(m.matches("pattern_694/file.log"));
    }
    @Test
    public void testGlobCase_695() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_695/*.txt", true);
        assertTrue(m.matches("pattern_695/file.txt"));
        assertFalse(m.matches("pattern_695/file.log"));
    }
    @Test
    public void testGlobCase_696() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_696/*.txt", true);
        assertTrue(m.matches("pattern_696/file.txt"));
        assertFalse(m.matches("pattern_696/file.log"));
    }
    @Test
    public void testGlobCase_697() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_697/*.txt", true);
        assertTrue(m.matches("pattern_697/file.txt"));
        assertFalse(m.matches("pattern_697/file.log"));
    }
    @Test
    public void testGlobCase_698() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_698/*.txt", true);
        assertTrue(m.matches("pattern_698/file.txt"));
        assertFalse(m.matches("pattern_698/file.log"));
    }
    @Test
    public void testGlobCase_699() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_699/*.txt", true);
        assertTrue(m.matches("pattern_699/file.txt"));
        assertFalse(m.matches("pattern_699/file.log"));
    }
    @Test
    public void testGlobCase_700() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_700/*.txt", true);
        assertTrue(m.matches("pattern_700/file.txt"));
        assertFalse(m.matches("pattern_700/file.log"));
    }
    @Test
    public void testGlobCase_701() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_701/*.txt", true);
        assertTrue(m.matches("pattern_701/file.txt"));
        assertFalse(m.matches("pattern_701/file.log"));
    }
    @Test
    public void testGlobCase_702() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_702/*.txt", true);
        assertTrue(m.matches("pattern_702/file.txt"));
        assertFalse(m.matches("pattern_702/file.log"));
    }
    @Test
    public void testGlobCase_703() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_703/*.txt", true);
        assertTrue(m.matches("pattern_703/file.txt"));
        assertFalse(m.matches("pattern_703/file.log"));
    }
    @Test
    public void testGlobCase_704() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_704/*.txt", true);
        assertTrue(m.matches("pattern_704/file.txt"));
        assertFalse(m.matches("pattern_704/file.log"));
    }
    @Test
    public void testGlobCase_705() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_705/*.txt", true);
        assertTrue(m.matches("pattern_705/file.txt"));
        assertFalse(m.matches("pattern_705/file.log"));
    }
    @Test
    public void testGlobCase_706() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_706/*.txt", true);
        assertTrue(m.matches("pattern_706/file.txt"));
        assertFalse(m.matches("pattern_706/file.log"));
    }
    @Test
    public void testGlobCase_707() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_707/*.txt", true);
        assertTrue(m.matches("pattern_707/file.txt"));
        assertFalse(m.matches("pattern_707/file.log"));
    }
    @Test
    public void testGlobCase_708() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_708/*.txt", true);
        assertTrue(m.matches("pattern_708/file.txt"));
        assertFalse(m.matches("pattern_708/file.log"));
    }
    @Test
    public void testGlobCase_709() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_709/*.txt", true);
        assertTrue(m.matches("pattern_709/file.txt"));
        assertFalse(m.matches("pattern_709/file.log"));
    }
    @Test
    public void testGlobCase_710() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_710/*.txt", true);
        assertTrue(m.matches("pattern_710/file.txt"));
        assertFalse(m.matches("pattern_710/file.log"));
    }
    @Test
    public void testGlobCase_711() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_711/*.txt", true);
        assertTrue(m.matches("pattern_711/file.txt"));
        assertFalse(m.matches("pattern_711/file.log"));
    }
    @Test
    public void testGlobCase_712() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_712/*.txt", true);
        assertTrue(m.matches("pattern_712/file.txt"));
        assertFalse(m.matches("pattern_712/file.log"));
    }
    @Test
    public void testGlobCase_713() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_713/*.txt", true);
        assertTrue(m.matches("pattern_713/file.txt"));
        assertFalse(m.matches("pattern_713/file.log"));
    }
    @Test
    public void testGlobCase_714() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_714/*.txt", true);
        assertTrue(m.matches("pattern_714/file.txt"));
        assertFalse(m.matches("pattern_714/file.log"));
    }
    @Test
    public void testGlobCase_715() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_715/*.txt", true);
        assertTrue(m.matches("pattern_715/file.txt"));
        assertFalse(m.matches("pattern_715/file.log"));
    }
    @Test
    public void testGlobCase_716() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_716/*.txt", true);
        assertTrue(m.matches("pattern_716/file.txt"));
        assertFalse(m.matches("pattern_716/file.log"));
    }
    @Test
    public void testGlobCase_717() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_717/*.txt", true);
        assertTrue(m.matches("pattern_717/file.txt"));
        assertFalse(m.matches("pattern_717/file.log"));
    }
    @Test
    public void testGlobCase_718() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_718/*.txt", true);
        assertTrue(m.matches("pattern_718/file.txt"));
        assertFalse(m.matches("pattern_718/file.log"));
    }
    @Test
    public void testGlobCase_719() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_719/*.txt", true);
        assertTrue(m.matches("pattern_719/file.txt"));
        assertFalse(m.matches("pattern_719/file.log"));
    }
    @Test
    public void testGlobCase_720() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_720/*.txt", true);
        assertTrue(m.matches("pattern_720/file.txt"));
        assertFalse(m.matches("pattern_720/file.log"));
    }
    @Test
    public void testGlobCase_721() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_721/*.txt", true);
        assertTrue(m.matches("pattern_721/file.txt"));
        assertFalse(m.matches("pattern_721/file.log"));
    }
    @Test
    public void testGlobCase_722() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_722/*.txt", true);
        assertTrue(m.matches("pattern_722/file.txt"));
        assertFalse(m.matches("pattern_722/file.log"));
    }
    @Test
    public void testGlobCase_723() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_723/*.txt", true);
        assertTrue(m.matches("pattern_723/file.txt"));
        assertFalse(m.matches("pattern_723/file.log"));
    }
    @Test
    public void testGlobCase_724() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_724/*.txt", true);
        assertTrue(m.matches("pattern_724/file.txt"));
        assertFalse(m.matches("pattern_724/file.log"));
    }
    @Test
    public void testGlobCase_725() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_725/*.txt", true);
        assertTrue(m.matches("pattern_725/file.txt"));
        assertFalse(m.matches("pattern_725/file.log"));
    }
    @Test
    public void testGlobCase_726() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_726/*.txt", true);
        assertTrue(m.matches("pattern_726/file.txt"));
        assertFalse(m.matches("pattern_726/file.log"));
    }
    @Test
    public void testGlobCase_727() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_727/*.txt", true);
        assertTrue(m.matches("pattern_727/file.txt"));
        assertFalse(m.matches("pattern_727/file.log"));
    }
    @Test
    public void testGlobCase_728() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_728/*.txt", true);
        assertTrue(m.matches("pattern_728/file.txt"));
        assertFalse(m.matches("pattern_728/file.log"));
    }
    @Test
    public void testGlobCase_729() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_729/*.txt", true);
        assertTrue(m.matches("pattern_729/file.txt"));
        assertFalse(m.matches("pattern_729/file.log"));
    }
    @Test
    public void testGlobCase_730() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_730/*.txt", true);
        assertTrue(m.matches("pattern_730/file.txt"));
        assertFalse(m.matches("pattern_730/file.log"));
    }
    @Test
    public void testGlobCase_731() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_731/*.txt", true);
        assertTrue(m.matches("pattern_731/file.txt"));
        assertFalse(m.matches("pattern_731/file.log"));
    }
    @Test
    public void testGlobCase_732() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_732/*.txt", true);
        assertTrue(m.matches("pattern_732/file.txt"));
        assertFalse(m.matches("pattern_732/file.log"));
    }
    @Test
    public void testGlobCase_733() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_733/*.txt", true);
        assertTrue(m.matches("pattern_733/file.txt"));
        assertFalse(m.matches("pattern_733/file.log"));
    }
    @Test
    public void testGlobCase_734() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_734/*.txt", true);
        assertTrue(m.matches("pattern_734/file.txt"));
        assertFalse(m.matches("pattern_734/file.log"));
    }
    @Test
    public void testGlobCase_735() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_735/*.txt", true);
        assertTrue(m.matches("pattern_735/file.txt"));
        assertFalse(m.matches("pattern_735/file.log"));
    }
    @Test
    public void testGlobCase_736() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_736/*.txt", true);
        assertTrue(m.matches("pattern_736/file.txt"));
        assertFalse(m.matches("pattern_736/file.log"));
    }
    @Test
    public void testGlobCase_737() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_737/*.txt", true);
        assertTrue(m.matches("pattern_737/file.txt"));
        assertFalse(m.matches("pattern_737/file.log"));
    }
    @Test
    public void testGlobCase_738() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_738/*.txt", true);
        assertTrue(m.matches("pattern_738/file.txt"));
        assertFalse(m.matches("pattern_738/file.log"));
    }
    @Test
    public void testGlobCase_739() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_739/*.txt", true);
        assertTrue(m.matches("pattern_739/file.txt"));
        assertFalse(m.matches("pattern_739/file.log"));
    }
    @Test
    public void testGlobCase_740() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_740/*.txt", true);
        assertTrue(m.matches("pattern_740/file.txt"));
        assertFalse(m.matches("pattern_740/file.log"));
    }
    @Test
    public void testGlobCase_741() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_741/*.txt", true);
        assertTrue(m.matches("pattern_741/file.txt"));
        assertFalse(m.matches("pattern_741/file.log"));
    }
    @Test
    public void testGlobCase_742() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_742/*.txt", true);
        assertTrue(m.matches("pattern_742/file.txt"));
        assertFalse(m.matches("pattern_742/file.log"));
    }
    @Test
    public void testGlobCase_743() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_743/*.txt", true);
        assertTrue(m.matches("pattern_743/file.txt"));
        assertFalse(m.matches("pattern_743/file.log"));
    }
    @Test
    public void testGlobCase_744() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_744/*.txt", true);
        assertTrue(m.matches("pattern_744/file.txt"));
        assertFalse(m.matches("pattern_744/file.log"));
    }
    @Test
    public void testGlobCase_745() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_745/*.txt", true);
        assertTrue(m.matches("pattern_745/file.txt"));
        assertFalse(m.matches("pattern_745/file.log"));
    }
    @Test
    public void testGlobCase_746() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_746/*.txt", true);
        assertTrue(m.matches("pattern_746/file.txt"));
        assertFalse(m.matches("pattern_746/file.log"));
    }
    @Test
    public void testGlobCase_747() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_747/*.txt", true);
        assertTrue(m.matches("pattern_747/file.txt"));
        assertFalse(m.matches("pattern_747/file.log"));
    }
    @Test
    public void testGlobCase_748() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_748/*.txt", true);
        assertTrue(m.matches("pattern_748/file.txt"));
        assertFalse(m.matches("pattern_748/file.log"));
    }
    @Test
    public void testGlobCase_749() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_749/*.txt", true);
        assertTrue(m.matches("pattern_749/file.txt"));
        assertFalse(m.matches("pattern_749/file.log"));
    }
    @Test
    public void testGlobCase_750() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_750/*.txt", true);
        assertTrue(m.matches("pattern_750/file.txt"));
        assertFalse(m.matches("pattern_750/file.log"));
    }
    @Test
    public void testGlobCase_751() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_751/*.txt", true);
        assertTrue(m.matches("pattern_751/file.txt"));
        assertFalse(m.matches("pattern_751/file.log"));
    }
    @Test
    public void testGlobCase_752() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_752/*.txt", true);
        assertTrue(m.matches("pattern_752/file.txt"));
        assertFalse(m.matches("pattern_752/file.log"));
    }
    @Test
    public void testGlobCase_753() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_753/*.txt", true);
        assertTrue(m.matches("pattern_753/file.txt"));
        assertFalse(m.matches("pattern_753/file.log"));
    }
    @Test
    public void testGlobCase_754() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_754/*.txt", true);
        assertTrue(m.matches("pattern_754/file.txt"));
        assertFalse(m.matches("pattern_754/file.log"));
    }
    @Test
    public void testGlobCase_755() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_755/*.txt", true);
        assertTrue(m.matches("pattern_755/file.txt"));
        assertFalse(m.matches("pattern_755/file.log"));
    }
    @Test
    public void testGlobCase_756() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_756/*.txt", true);
        assertTrue(m.matches("pattern_756/file.txt"));
        assertFalse(m.matches("pattern_756/file.log"));
    }
    @Test
    public void testGlobCase_757() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_757/*.txt", true);
        assertTrue(m.matches("pattern_757/file.txt"));
        assertFalse(m.matches("pattern_757/file.log"));
    }
    @Test
    public void testGlobCase_758() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_758/*.txt", true);
        assertTrue(m.matches("pattern_758/file.txt"));
        assertFalse(m.matches("pattern_758/file.log"));
    }
    @Test
    public void testGlobCase_759() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_759/*.txt", true);
        assertTrue(m.matches("pattern_759/file.txt"));
        assertFalse(m.matches("pattern_759/file.log"));
    }
    @Test
    public void testGlobCase_760() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_760/*.txt", true);
        assertTrue(m.matches("pattern_760/file.txt"));
        assertFalse(m.matches("pattern_760/file.log"));
    }
    @Test
    public void testGlobCase_761() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_761/*.txt", true);
        assertTrue(m.matches("pattern_761/file.txt"));
        assertFalse(m.matches("pattern_761/file.log"));
    }
    @Test
    public void testGlobCase_762() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_762/*.txt", true);
        assertTrue(m.matches("pattern_762/file.txt"));
        assertFalse(m.matches("pattern_762/file.log"));
    }
    @Test
    public void testGlobCase_763() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_763/*.txt", true);
        assertTrue(m.matches("pattern_763/file.txt"));
        assertFalse(m.matches("pattern_763/file.log"));
    }
    @Test
    public void testGlobCase_764() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_764/*.txt", true);
        assertTrue(m.matches("pattern_764/file.txt"));
        assertFalse(m.matches("pattern_764/file.log"));
    }
    @Test
    public void testGlobCase_765() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_765/*.txt", true);
        assertTrue(m.matches("pattern_765/file.txt"));
        assertFalse(m.matches("pattern_765/file.log"));
    }
    @Test
    public void testGlobCase_766() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_766/*.txt", true);
        assertTrue(m.matches("pattern_766/file.txt"));
        assertFalse(m.matches("pattern_766/file.log"));
    }
    @Test
    public void testGlobCase_767() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_767/*.txt", true);
        assertTrue(m.matches("pattern_767/file.txt"));
        assertFalse(m.matches("pattern_767/file.log"));
    }
    @Test
    public void testGlobCase_768() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_768/*.txt", true);
        assertTrue(m.matches("pattern_768/file.txt"));
        assertFalse(m.matches("pattern_768/file.log"));
    }
    @Test
    public void testGlobCase_769() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_769/*.txt", true);
        assertTrue(m.matches("pattern_769/file.txt"));
        assertFalse(m.matches("pattern_769/file.log"));
    }
    @Test
    public void testGlobCase_770() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_770/*.txt", true);
        assertTrue(m.matches("pattern_770/file.txt"));
        assertFalse(m.matches("pattern_770/file.log"));
    }
    @Test
    public void testGlobCase_771() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_771/*.txt", true);
        assertTrue(m.matches("pattern_771/file.txt"));
        assertFalse(m.matches("pattern_771/file.log"));
    }
    @Test
    public void testGlobCase_772() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_772/*.txt", true);
        assertTrue(m.matches("pattern_772/file.txt"));
        assertFalse(m.matches("pattern_772/file.log"));
    }
    @Test
    public void testGlobCase_773() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_773/*.txt", true);
        assertTrue(m.matches("pattern_773/file.txt"));
        assertFalse(m.matches("pattern_773/file.log"));
    }
    @Test
    public void testGlobCase_774() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_774/*.txt", true);
        assertTrue(m.matches("pattern_774/file.txt"));
        assertFalse(m.matches("pattern_774/file.log"));
    }
    @Test
    public void testGlobCase_775() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_775/*.txt", true);
        assertTrue(m.matches("pattern_775/file.txt"));
        assertFalse(m.matches("pattern_775/file.log"));
    }
    @Test
    public void testGlobCase_776() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_776/*.txt", true);
        assertTrue(m.matches("pattern_776/file.txt"));
        assertFalse(m.matches("pattern_776/file.log"));
    }
    @Test
    public void testGlobCase_777() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_777/*.txt", true);
        assertTrue(m.matches("pattern_777/file.txt"));
        assertFalse(m.matches("pattern_777/file.log"));
    }
    @Test
    public void testGlobCase_778() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_778/*.txt", true);
        assertTrue(m.matches("pattern_778/file.txt"));
        assertFalse(m.matches("pattern_778/file.log"));
    }
    @Test
    public void testGlobCase_779() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_779/*.txt", true);
        assertTrue(m.matches("pattern_779/file.txt"));
        assertFalse(m.matches("pattern_779/file.log"));
    }
    @Test
    public void testGlobCase_780() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_780/*.txt", true);
        assertTrue(m.matches("pattern_780/file.txt"));
        assertFalse(m.matches("pattern_780/file.log"));
    }
    @Test
    public void testGlobCase_781() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_781/*.txt", true);
        assertTrue(m.matches("pattern_781/file.txt"));
        assertFalse(m.matches("pattern_781/file.log"));
    }
    @Test
    public void testGlobCase_782() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_782/*.txt", true);
        assertTrue(m.matches("pattern_782/file.txt"));
        assertFalse(m.matches("pattern_782/file.log"));
    }
    @Test
    public void testGlobCase_783() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_783/*.txt", true);
        assertTrue(m.matches("pattern_783/file.txt"));
        assertFalse(m.matches("pattern_783/file.log"));
    }
    @Test
    public void testGlobCase_784() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_784/*.txt", true);
        assertTrue(m.matches("pattern_784/file.txt"));
        assertFalse(m.matches("pattern_784/file.log"));
    }
    @Test
    public void testGlobCase_785() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_785/*.txt", true);
        assertTrue(m.matches("pattern_785/file.txt"));
        assertFalse(m.matches("pattern_785/file.log"));
    }
    @Test
    public void testGlobCase_786() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_786/*.txt", true);
        assertTrue(m.matches("pattern_786/file.txt"));
        assertFalse(m.matches("pattern_786/file.log"));
    }
    @Test
    public void testGlobCase_787() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_787/*.txt", true);
        assertTrue(m.matches("pattern_787/file.txt"));
        assertFalse(m.matches("pattern_787/file.log"));
    }
    @Test
    public void testGlobCase_788() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_788/*.txt", true);
        assertTrue(m.matches("pattern_788/file.txt"));
        assertFalse(m.matches("pattern_788/file.log"));
    }
    @Test
    public void testGlobCase_789() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_789/*.txt", true);
        assertTrue(m.matches("pattern_789/file.txt"));
        assertFalse(m.matches("pattern_789/file.log"));
    }
    @Test
    public void testGlobCase_790() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_790/*.txt", true);
        assertTrue(m.matches("pattern_790/file.txt"));
        assertFalse(m.matches("pattern_790/file.log"));
    }
    @Test
    public void testGlobCase_791() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_791/*.txt", true);
        assertTrue(m.matches("pattern_791/file.txt"));
        assertFalse(m.matches("pattern_791/file.log"));
    }
    @Test
    public void testGlobCase_792() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_792/*.txt", true);
        assertTrue(m.matches("pattern_792/file.txt"));
        assertFalse(m.matches("pattern_792/file.log"));
    }
    @Test
    public void testGlobCase_793() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_793/*.txt", true);
        assertTrue(m.matches("pattern_793/file.txt"));
        assertFalse(m.matches("pattern_793/file.log"));
    }
    @Test
    public void testGlobCase_794() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_794/*.txt", true);
        assertTrue(m.matches("pattern_794/file.txt"));
        assertFalse(m.matches("pattern_794/file.log"));
    }
    @Test
    public void testGlobCase_795() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_795/*.txt", true);
        assertTrue(m.matches("pattern_795/file.txt"));
        assertFalse(m.matches("pattern_795/file.log"));
    }
    @Test
    public void testGlobCase_796() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_796/*.txt", true);
        assertTrue(m.matches("pattern_796/file.txt"));
        assertFalse(m.matches("pattern_796/file.log"));
    }
    @Test
    public void testGlobCase_797() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_797/*.txt", true);
        assertTrue(m.matches("pattern_797/file.txt"));
        assertFalse(m.matches("pattern_797/file.log"));
    }
    @Test
    public void testGlobCase_798() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_798/*.txt", true);
        assertTrue(m.matches("pattern_798/file.txt"));
        assertFalse(m.matches("pattern_798/file.log"));
    }
    @Test
    public void testGlobCase_799() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_799/*.txt", true);
        assertTrue(m.matches("pattern_799/file.txt"));
        assertFalse(m.matches("pattern_799/file.log"));
    }
    @Test
    public void testGlobCase_800() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_800/*.txt", true);
        assertTrue(m.matches("pattern_800/file.txt"));
        assertFalse(m.matches("pattern_800/file.log"));
    }
    @Test
    public void testGlobCase_801() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_801/*.txt", true);
        assertTrue(m.matches("pattern_801/file.txt"));
        assertFalse(m.matches("pattern_801/file.log"));
    }
    @Test
    public void testGlobCase_802() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_802/*.txt", true);
        assertTrue(m.matches("pattern_802/file.txt"));
        assertFalse(m.matches("pattern_802/file.log"));
    }
    @Test
    public void testGlobCase_803() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_803/*.txt", true);
        assertTrue(m.matches("pattern_803/file.txt"));
        assertFalse(m.matches("pattern_803/file.log"));
    }
    @Test
    public void testGlobCase_804() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_804/*.txt", true);
        assertTrue(m.matches("pattern_804/file.txt"));
        assertFalse(m.matches("pattern_804/file.log"));
    }
    @Test
    public void testGlobCase_805() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_805/*.txt", true);
        assertTrue(m.matches("pattern_805/file.txt"));
        assertFalse(m.matches("pattern_805/file.log"));
    }
    @Test
    public void testGlobCase_806() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_806/*.txt", true);
        assertTrue(m.matches("pattern_806/file.txt"));
        assertFalse(m.matches("pattern_806/file.log"));
    }
    @Test
    public void testGlobCase_807() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_807/*.txt", true);
        assertTrue(m.matches("pattern_807/file.txt"));
        assertFalse(m.matches("pattern_807/file.log"));
    }
    @Test
    public void testGlobCase_808() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_808/*.txt", true);
        assertTrue(m.matches("pattern_808/file.txt"));
        assertFalse(m.matches("pattern_808/file.log"));
    }
    @Test
    public void testGlobCase_809() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_809/*.txt", true);
        assertTrue(m.matches("pattern_809/file.txt"));
        assertFalse(m.matches("pattern_809/file.log"));
    }
    @Test
    public void testGlobCase_810() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_810/*.txt", true);
        assertTrue(m.matches("pattern_810/file.txt"));
        assertFalse(m.matches("pattern_810/file.log"));
    }
    @Test
    public void testGlobCase_811() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_811/*.txt", true);
        assertTrue(m.matches("pattern_811/file.txt"));
        assertFalse(m.matches("pattern_811/file.log"));
    }
    @Test
    public void testGlobCase_812() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_812/*.txt", true);
        assertTrue(m.matches("pattern_812/file.txt"));
        assertFalse(m.matches("pattern_812/file.log"));
    }
    @Test
    public void testGlobCase_813() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_813/*.txt", true);
        assertTrue(m.matches("pattern_813/file.txt"));
        assertFalse(m.matches("pattern_813/file.log"));
    }
    @Test
    public void testGlobCase_814() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_814/*.txt", true);
        assertTrue(m.matches("pattern_814/file.txt"));
        assertFalse(m.matches("pattern_814/file.log"));
    }
    @Test
    public void testGlobCase_815() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_815/*.txt", true);
        assertTrue(m.matches("pattern_815/file.txt"));
        assertFalse(m.matches("pattern_815/file.log"));
    }
    @Test
    public void testGlobCase_816() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_816/*.txt", true);
        assertTrue(m.matches("pattern_816/file.txt"));
        assertFalse(m.matches("pattern_816/file.log"));
    }
    @Test
    public void testGlobCase_817() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_817/*.txt", true);
        assertTrue(m.matches("pattern_817/file.txt"));
        assertFalse(m.matches("pattern_817/file.log"));
    }
    @Test
    public void testGlobCase_818() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_818/*.txt", true);
        assertTrue(m.matches("pattern_818/file.txt"));
        assertFalse(m.matches("pattern_818/file.log"));
    }
    @Test
    public void testGlobCase_819() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_819/*.txt", true);
        assertTrue(m.matches("pattern_819/file.txt"));
        assertFalse(m.matches("pattern_819/file.log"));
    }
    @Test
    public void testGlobCase_820() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_820/*.txt", true);
        assertTrue(m.matches("pattern_820/file.txt"));
        assertFalse(m.matches("pattern_820/file.log"));
    }
    @Test
    public void testGlobCase_821() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_821/*.txt", true);
        assertTrue(m.matches("pattern_821/file.txt"));
        assertFalse(m.matches("pattern_821/file.log"));
    }
    @Test
    public void testGlobCase_822() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_822/*.txt", true);
        assertTrue(m.matches("pattern_822/file.txt"));
        assertFalse(m.matches("pattern_822/file.log"));
    }
    @Test
    public void testGlobCase_823() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_823/*.txt", true);
        assertTrue(m.matches("pattern_823/file.txt"));
        assertFalse(m.matches("pattern_823/file.log"));
    }
    @Test
    public void testGlobCase_824() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_824/*.txt", true);
        assertTrue(m.matches("pattern_824/file.txt"));
        assertFalse(m.matches("pattern_824/file.log"));
    }
    @Test
    public void testGlobCase_825() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_825/*.txt", true);
        assertTrue(m.matches("pattern_825/file.txt"));
        assertFalse(m.matches("pattern_825/file.log"));
    }
    @Test
    public void testGlobCase_826() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_826/*.txt", true);
        assertTrue(m.matches("pattern_826/file.txt"));
        assertFalse(m.matches("pattern_826/file.log"));
    }
    @Test
    public void testGlobCase_827() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_827/*.txt", true);
        assertTrue(m.matches("pattern_827/file.txt"));
        assertFalse(m.matches("pattern_827/file.log"));
    }
    @Test
    public void testGlobCase_828() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_828/*.txt", true);
        assertTrue(m.matches("pattern_828/file.txt"));
        assertFalse(m.matches("pattern_828/file.log"));
    }
    @Test
    public void testGlobCase_829() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_829/*.txt", true);
        assertTrue(m.matches("pattern_829/file.txt"));
        assertFalse(m.matches("pattern_829/file.log"));
    }
    @Test
    public void testGlobCase_830() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_830/*.txt", true);
        assertTrue(m.matches("pattern_830/file.txt"));
        assertFalse(m.matches("pattern_830/file.log"));
    }
    @Test
    public void testGlobCase_831() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_831/*.txt", true);
        assertTrue(m.matches("pattern_831/file.txt"));
        assertFalse(m.matches("pattern_831/file.log"));
    }
    @Test
    public void testGlobCase_832() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_832/*.txt", true);
        assertTrue(m.matches("pattern_832/file.txt"));
        assertFalse(m.matches("pattern_832/file.log"));
    }
    @Test
    public void testGlobCase_833() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_833/*.txt", true);
        assertTrue(m.matches("pattern_833/file.txt"));
        assertFalse(m.matches("pattern_833/file.log"));
    }
    @Test
    public void testGlobCase_834() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_834/*.txt", true);
        assertTrue(m.matches("pattern_834/file.txt"));
        assertFalse(m.matches("pattern_834/file.log"));
    }
    @Test
    public void testGlobCase_835() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_835/*.txt", true);
        assertTrue(m.matches("pattern_835/file.txt"));
        assertFalse(m.matches("pattern_835/file.log"));
    }
    @Test
    public void testGlobCase_836() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_836/*.txt", true);
        assertTrue(m.matches("pattern_836/file.txt"));
        assertFalse(m.matches("pattern_836/file.log"));
    }
    @Test
    public void testGlobCase_837() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_837/*.txt", true);
        assertTrue(m.matches("pattern_837/file.txt"));
        assertFalse(m.matches("pattern_837/file.log"));
    }
    @Test
    public void testGlobCase_838() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_838/*.txt", true);
        assertTrue(m.matches("pattern_838/file.txt"));
        assertFalse(m.matches("pattern_838/file.log"));
    }
    @Test
    public void testGlobCase_839() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_839/*.txt", true);
        assertTrue(m.matches("pattern_839/file.txt"));
        assertFalse(m.matches("pattern_839/file.log"));
    }
    @Test
    public void testGlobCase_840() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_840/*.txt", true);
        assertTrue(m.matches("pattern_840/file.txt"));
        assertFalse(m.matches("pattern_840/file.log"));
    }
    @Test
    public void testGlobCase_841() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_841/*.txt", true);
        assertTrue(m.matches("pattern_841/file.txt"));
        assertFalse(m.matches("pattern_841/file.log"));
    }
    @Test
    public void testGlobCase_842() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_842/*.txt", true);
        assertTrue(m.matches("pattern_842/file.txt"));
        assertFalse(m.matches("pattern_842/file.log"));
    }
    @Test
    public void testGlobCase_843() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_843/*.txt", true);
        assertTrue(m.matches("pattern_843/file.txt"));
        assertFalse(m.matches("pattern_843/file.log"));
    }
    @Test
    public void testGlobCase_844() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_844/*.txt", true);
        assertTrue(m.matches("pattern_844/file.txt"));
        assertFalse(m.matches("pattern_844/file.log"));
    }
    @Test
    public void testGlobCase_845() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_845/*.txt", true);
        assertTrue(m.matches("pattern_845/file.txt"));
        assertFalse(m.matches("pattern_845/file.log"));
    }
    @Test
    public void testGlobCase_846() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_846/*.txt", true);
        assertTrue(m.matches("pattern_846/file.txt"));
        assertFalse(m.matches("pattern_846/file.log"));
    }
    @Test
    public void testGlobCase_847() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_847/*.txt", true);
        assertTrue(m.matches("pattern_847/file.txt"));
        assertFalse(m.matches("pattern_847/file.log"));
    }
    @Test
    public void testGlobCase_848() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_848/*.txt", true);
        assertTrue(m.matches("pattern_848/file.txt"));
        assertFalse(m.matches("pattern_848/file.log"));
    }
    @Test
    public void testGlobCase_849() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_849/*.txt", true);
        assertTrue(m.matches("pattern_849/file.txt"));
        assertFalse(m.matches("pattern_849/file.log"));
    }
    @Test
    public void testGlobCase_850() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_850/*.txt", true);
        assertTrue(m.matches("pattern_850/file.txt"));
        assertFalse(m.matches("pattern_850/file.log"));
    }
    @Test
    public void testGlobCase_851() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_851/*.txt", true);
        assertTrue(m.matches("pattern_851/file.txt"));
        assertFalse(m.matches("pattern_851/file.log"));
    }
    @Test
    public void testGlobCase_852() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_852/*.txt", true);
        assertTrue(m.matches("pattern_852/file.txt"));
        assertFalse(m.matches("pattern_852/file.log"));
    }
    @Test
    public void testGlobCase_853() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_853/*.txt", true);
        assertTrue(m.matches("pattern_853/file.txt"));
        assertFalse(m.matches("pattern_853/file.log"));
    }
    @Test
    public void testGlobCase_854() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_854/*.txt", true);
        assertTrue(m.matches("pattern_854/file.txt"));
        assertFalse(m.matches("pattern_854/file.log"));
    }
    @Test
    public void testGlobCase_855() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_855/*.txt", true);
        assertTrue(m.matches("pattern_855/file.txt"));
        assertFalse(m.matches("pattern_855/file.log"));
    }
    @Test
    public void testGlobCase_856() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_856/*.txt", true);
        assertTrue(m.matches("pattern_856/file.txt"));
        assertFalse(m.matches("pattern_856/file.log"));
    }
    @Test
    public void testGlobCase_857() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_857/*.txt", true);
        assertTrue(m.matches("pattern_857/file.txt"));
        assertFalse(m.matches("pattern_857/file.log"));
    }
    @Test
    public void testGlobCase_858() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_858/*.txt", true);
        assertTrue(m.matches("pattern_858/file.txt"));
        assertFalse(m.matches("pattern_858/file.log"));
    }
    @Test
    public void testGlobCase_859() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_859/*.txt", true);
        assertTrue(m.matches("pattern_859/file.txt"));
        assertFalse(m.matches("pattern_859/file.log"));
    }
    @Test
    public void testGlobCase_860() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_860/*.txt", true);
        assertTrue(m.matches("pattern_860/file.txt"));
        assertFalse(m.matches("pattern_860/file.log"));
    }
    @Test
    public void testGlobCase_861() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_861/*.txt", true);
        assertTrue(m.matches("pattern_861/file.txt"));
        assertFalse(m.matches("pattern_861/file.log"));
    }
    @Test
    public void testGlobCase_862() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_862/*.txt", true);
        assertTrue(m.matches("pattern_862/file.txt"));
        assertFalse(m.matches("pattern_862/file.log"));
    }
    @Test
    public void testGlobCase_863() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_863/*.txt", true);
        assertTrue(m.matches("pattern_863/file.txt"));
        assertFalse(m.matches("pattern_863/file.log"));
    }
    @Test
    public void testGlobCase_864() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_864/*.txt", true);
        assertTrue(m.matches("pattern_864/file.txt"));
        assertFalse(m.matches("pattern_864/file.log"));
    }
    @Test
    public void testGlobCase_865() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_865/*.txt", true);
        assertTrue(m.matches("pattern_865/file.txt"));
        assertFalse(m.matches("pattern_865/file.log"));
    }
    @Test
    public void testGlobCase_866() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_866/*.txt", true);
        assertTrue(m.matches("pattern_866/file.txt"));
        assertFalse(m.matches("pattern_866/file.log"));
    }
    @Test
    public void testGlobCase_867() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_867/*.txt", true);
        assertTrue(m.matches("pattern_867/file.txt"));
        assertFalse(m.matches("pattern_867/file.log"));
    }
    @Test
    public void testGlobCase_868() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_868/*.txt", true);
        assertTrue(m.matches("pattern_868/file.txt"));
        assertFalse(m.matches("pattern_868/file.log"));
    }
    @Test
    public void testGlobCase_869() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_869/*.txt", true);
        assertTrue(m.matches("pattern_869/file.txt"));
        assertFalse(m.matches("pattern_869/file.log"));
    }
    @Test
    public void testGlobCase_870() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_870/*.txt", true);
        assertTrue(m.matches("pattern_870/file.txt"));
        assertFalse(m.matches("pattern_870/file.log"));
    }
    @Test
    public void testGlobCase_871() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_871/*.txt", true);
        assertTrue(m.matches("pattern_871/file.txt"));
        assertFalse(m.matches("pattern_871/file.log"));
    }
    @Test
    public void testGlobCase_872() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_872/*.txt", true);
        assertTrue(m.matches("pattern_872/file.txt"));
        assertFalse(m.matches("pattern_872/file.log"));
    }
    @Test
    public void testGlobCase_873() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_873/*.txt", true);
        assertTrue(m.matches("pattern_873/file.txt"));
        assertFalse(m.matches("pattern_873/file.log"));
    }
    @Test
    public void testGlobCase_874() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_874/*.txt", true);
        assertTrue(m.matches("pattern_874/file.txt"));
        assertFalse(m.matches("pattern_874/file.log"));
    }
    @Test
    public void testGlobCase_875() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_875/*.txt", true);
        assertTrue(m.matches("pattern_875/file.txt"));
        assertFalse(m.matches("pattern_875/file.log"));
    }
    @Test
    public void testGlobCase_876() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_876/*.txt", true);
        assertTrue(m.matches("pattern_876/file.txt"));
        assertFalse(m.matches("pattern_876/file.log"));
    }
    @Test
    public void testGlobCase_877() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_877/*.txt", true);
        assertTrue(m.matches("pattern_877/file.txt"));
        assertFalse(m.matches("pattern_877/file.log"));
    }
    @Test
    public void testGlobCase_878() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_878/*.txt", true);
        assertTrue(m.matches("pattern_878/file.txt"));
        assertFalse(m.matches("pattern_878/file.log"));
    }
    @Test
    public void testGlobCase_879() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_879/*.txt", true);
        assertTrue(m.matches("pattern_879/file.txt"));
        assertFalse(m.matches("pattern_879/file.log"));
    }
    @Test
    public void testGlobCase_880() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_880/*.txt", true);
        assertTrue(m.matches("pattern_880/file.txt"));
        assertFalse(m.matches("pattern_880/file.log"));
    }
    @Test
    public void testGlobCase_881() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_881/*.txt", true);
        assertTrue(m.matches("pattern_881/file.txt"));
        assertFalse(m.matches("pattern_881/file.log"));
    }
    @Test
    public void testGlobCase_882() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_882/*.txt", true);
        assertTrue(m.matches("pattern_882/file.txt"));
        assertFalse(m.matches("pattern_882/file.log"));
    }
    @Test
    public void testGlobCase_883() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_883/*.txt", true);
        assertTrue(m.matches("pattern_883/file.txt"));
        assertFalse(m.matches("pattern_883/file.log"));
    }
    @Test
    public void testGlobCase_884() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_884/*.txt", true);
        assertTrue(m.matches("pattern_884/file.txt"));
        assertFalse(m.matches("pattern_884/file.log"));
    }
    @Test
    public void testGlobCase_885() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_885/*.txt", true);
        assertTrue(m.matches("pattern_885/file.txt"));
        assertFalse(m.matches("pattern_885/file.log"));
    }
    @Test
    public void testGlobCase_886() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_886/*.txt", true);
        assertTrue(m.matches("pattern_886/file.txt"));
        assertFalse(m.matches("pattern_886/file.log"));
    }
    @Test
    public void testGlobCase_887() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_887/*.txt", true);
        assertTrue(m.matches("pattern_887/file.txt"));
        assertFalse(m.matches("pattern_887/file.log"));
    }
    @Test
    public void testGlobCase_888() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_888/*.txt", true);
        assertTrue(m.matches("pattern_888/file.txt"));
        assertFalse(m.matches("pattern_888/file.log"));
    }
    @Test
    public void testGlobCase_889() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_889/*.txt", true);
        assertTrue(m.matches("pattern_889/file.txt"));
        assertFalse(m.matches("pattern_889/file.log"));
    }
    @Test
    public void testGlobCase_890() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_890/*.txt", true);
        assertTrue(m.matches("pattern_890/file.txt"));
        assertFalse(m.matches("pattern_890/file.log"));
    }
    @Test
    public void testGlobCase_891() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_891/*.txt", true);
        assertTrue(m.matches("pattern_891/file.txt"));
        assertFalse(m.matches("pattern_891/file.log"));
    }
    @Test
    public void testGlobCase_892() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_892/*.txt", true);
        assertTrue(m.matches("pattern_892/file.txt"));
        assertFalse(m.matches("pattern_892/file.log"));
    }
    @Test
    public void testGlobCase_893() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_893/*.txt", true);
        assertTrue(m.matches("pattern_893/file.txt"));
        assertFalse(m.matches("pattern_893/file.log"));
    }
    @Test
    public void testGlobCase_894() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_894/*.txt", true);
        assertTrue(m.matches("pattern_894/file.txt"));
        assertFalse(m.matches("pattern_894/file.log"));
    }
    @Test
    public void testGlobCase_895() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_895/*.txt", true);
        assertTrue(m.matches("pattern_895/file.txt"));
        assertFalse(m.matches("pattern_895/file.log"));
    }
    @Test
    public void testGlobCase_896() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_896/*.txt", true);
        assertTrue(m.matches("pattern_896/file.txt"));
        assertFalse(m.matches("pattern_896/file.log"));
    }
    @Test
    public void testGlobCase_897() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_897/*.txt", true);
        assertTrue(m.matches("pattern_897/file.txt"));
        assertFalse(m.matches("pattern_897/file.log"));
    }
    @Test
    public void testGlobCase_898() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_898/*.txt", true);
        assertTrue(m.matches("pattern_898/file.txt"));
        assertFalse(m.matches("pattern_898/file.log"));
    }
    @Test
    public void testGlobCase_899() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_899/*.txt", true);
        assertTrue(m.matches("pattern_899/file.txt"));
        assertFalse(m.matches("pattern_899/file.log"));
    }
    @Test
    public void testGlobCase_900() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_900/*.txt", true);
        assertTrue(m.matches("pattern_900/file.txt"));
        assertFalse(m.matches("pattern_900/file.log"));
    }
    @Test
    public void testGlobCase_901() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_901/*.txt", true);
        assertTrue(m.matches("pattern_901/file.txt"));
        assertFalse(m.matches("pattern_901/file.log"));
    }
    @Test
    public void testGlobCase_902() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_902/*.txt", true);
        assertTrue(m.matches("pattern_902/file.txt"));
        assertFalse(m.matches("pattern_902/file.log"));
    }
    @Test
    public void testGlobCase_903() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_903/*.txt", true);
        assertTrue(m.matches("pattern_903/file.txt"));
        assertFalse(m.matches("pattern_903/file.log"));
    }
    @Test
    public void testGlobCase_904() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_904/*.txt", true);
        assertTrue(m.matches("pattern_904/file.txt"));
        assertFalse(m.matches("pattern_904/file.log"));
    }
    @Test
    public void testGlobCase_905() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_905/*.txt", true);
        assertTrue(m.matches("pattern_905/file.txt"));
        assertFalse(m.matches("pattern_905/file.log"));
    }
    @Test
    public void testGlobCase_906() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_906/*.txt", true);
        assertTrue(m.matches("pattern_906/file.txt"));
        assertFalse(m.matches("pattern_906/file.log"));
    }
    @Test
    public void testGlobCase_907() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_907/*.txt", true);
        assertTrue(m.matches("pattern_907/file.txt"));
        assertFalse(m.matches("pattern_907/file.log"));
    }
    @Test
    public void testGlobCase_908() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_908/*.txt", true);
        assertTrue(m.matches("pattern_908/file.txt"));
        assertFalse(m.matches("pattern_908/file.log"));
    }
    @Test
    public void testGlobCase_909() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_909/*.txt", true);
        assertTrue(m.matches("pattern_909/file.txt"));
        assertFalse(m.matches("pattern_909/file.log"));
    }
    @Test
    public void testGlobCase_910() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_910/*.txt", true);
        assertTrue(m.matches("pattern_910/file.txt"));
        assertFalse(m.matches("pattern_910/file.log"));
    }
    @Test
    public void testGlobCase_911() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_911/*.txt", true);
        assertTrue(m.matches("pattern_911/file.txt"));
        assertFalse(m.matches("pattern_911/file.log"));
    }
    @Test
    public void testGlobCase_912() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_912/*.txt", true);
        assertTrue(m.matches("pattern_912/file.txt"));
        assertFalse(m.matches("pattern_912/file.log"));
    }
    @Test
    public void testGlobCase_913() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_913/*.txt", true);
        assertTrue(m.matches("pattern_913/file.txt"));
        assertFalse(m.matches("pattern_913/file.log"));
    }
    @Test
    public void testGlobCase_914() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_914/*.txt", true);
        assertTrue(m.matches("pattern_914/file.txt"));
        assertFalse(m.matches("pattern_914/file.log"));
    }
    @Test
    public void testGlobCase_915() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_915/*.txt", true);
        assertTrue(m.matches("pattern_915/file.txt"));
        assertFalse(m.matches("pattern_915/file.log"));
    }
    @Test
    public void testGlobCase_916() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_916/*.txt", true);
        assertTrue(m.matches("pattern_916/file.txt"));
        assertFalse(m.matches("pattern_916/file.log"));
    }
    @Test
    public void testGlobCase_917() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_917/*.txt", true);
        assertTrue(m.matches("pattern_917/file.txt"));
        assertFalse(m.matches("pattern_917/file.log"));
    }
    @Test
    public void testGlobCase_918() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_918/*.txt", true);
        assertTrue(m.matches("pattern_918/file.txt"));
        assertFalse(m.matches("pattern_918/file.log"));
    }
    @Test
    public void testGlobCase_919() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_919/*.txt", true);
        assertTrue(m.matches("pattern_919/file.txt"));
        assertFalse(m.matches("pattern_919/file.log"));
    }
    @Test
    public void testGlobCase_920() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_920/*.txt", true);
        assertTrue(m.matches("pattern_920/file.txt"));
        assertFalse(m.matches("pattern_920/file.log"));
    }
    @Test
    public void testGlobCase_921() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_921/*.txt", true);
        assertTrue(m.matches("pattern_921/file.txt"));
        assertFalse(m.matches("pattern_921/file.log"));
    }
    @Test
    public void testGlobCase_922() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_922/*.txt", true);
        assertTrue(m.matches("pattern_922/file.txt"));
        assertFalse(m.matches("pattern_922/file.log"));
    }
    @Test
    public void testGlobCase_923() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_923/*.txt", true);
        assertTrue(m.matches("pattern_923/file.txt"));
        assertFalse(m.matches("pattern_923/file.log"));
    }
    @Test
    public void testGlobCase_924() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_924/*.txt", true);
        assertTrue(m.matches("pattern_924/file.txt"));
        assertFalse(m.matches("pattern_924/file.log"));
    }
    @Test
    public void testGlobCase_925() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_925/*.txt", true);
        assertTrue(m.matches("pattern_925/file.txt"));
        assertFalse(m.matches("pattern_925/file.log"));
    }
    @Test
    public void testGlobCase_926() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_926/*.txt", true);
        assertTrue(m.matches("pattern_926/file.txt"));
        assertFalse(m.matches("pattern_926/file.log"));
    }
    @Test
    public void testGlobCase_927() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_927/*.txt", true);
        assertTrue(m.matches("pattern_927/file.txt"));
        assertFalse(m.matches("pattern_927/file.log"));
    }
    @Test
    public void testGlobCase_928() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_928/*.txt", true);
        assertTrue(m.matches("pattern_928/file.txt"));
        assertFalse(m.matches("pattern_928/file.log"));
    }
    @Test
    public void testGlobCase_929() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_929/*.txt", true);
        assertTrue(m.matches("pattern_929/file.txt"));
        assertFalse(m.matches("pattern_929/file.log"));
    }
    @Test
    public void testGlobCase_930() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_930/*.txt", true);
        assertTrue(m.matches("pattern_930/file.txt"));
        assertFalse(m.matches("pattern_930/file.log"));
    }
    @Test
    public void testGlobCase_931() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_931/*.txt", true);
        assertTrue(m.matches("pattern_931/file.txt"));
        assertFalse(m.matches("pattern_931/file.log"));
    }
    @Test
    public void testGlobCase_932() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_932/*.txt", true);
        assertTrue(m.matches("pattern_932/file.txt"));
        assertFalse(m.matches("pattern_932/file.log"));
    }
    @Test
    public void testGlobCase_933() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_933/*.txt", true);
        assertTrue(m.matches("pattern_933/file.txt"));
        assertFalse(m.matches("pattern_933/file.log"));
    }
    @Test
    public void testGlobCase_934() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_934/*.txt", true);
        assertTrue(m.matches("pattern_934/file.txt"));
        assertFalse(m.matches("pattern_934/file.log"));
    }
    @Test
    public void testGlobCase_935() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_935/*.txt", true);
        assertTrue(m.matches("pattern_935/file.txt"));
        assertFalse(m.matches("pattern_935/file.log"));
    }
    @Test
    public void testGlobCase_936() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_936/*.txt", true);
        assertTrue(m.matches("pattern_936/file.txt"));
        assertFalse(m.matches("pattern_936/file.log"));
    }
    @Test
    public void testGlobCase_937() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_937/*.txt", true);
        assertTrue(m.matches("pattern_937/file.txt"));
        assertFalse(m.matches("pattern_937/file.log"));
    }
    @Test
    public void testGlobCase_938() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_938/*.txt", true);
        assertTrue(m.matches("pattern_938/file.txt"));
        assertFalse(m.matches("pattern_938/file.log"));
    }
    @Test
    public void testGlobCase_939() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_939/*.txt", true);
        assertTrue(m.matches("pattern_939/file.txt"));
        assertFalse(m.matches("pattern_939/file.log"));
    }
    @Test
    public void testGlobCase_940() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_940/*.txt", true);
        assertTrue(m.matches("pattern_940/file.txt"));
        assertFalse(m.matches("pattern_940/file.log"));
    }
    @Test
    public void testGlobCase_941() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_941/*.txt", true);
        assertTrue(m.matches("pattern_941/file.txt"));
        assertFalse(m.matches("pattern_941/file.log"));
    }
    @Test
    public void testGlobCase_942() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_942/*.txt", true);
        assertTrue(m.matches("pattern_942/file.txt"));
        assertFalse(m.matches("pattern_942/file.log"));
    }
    @Test
    public void testGlobCase_943() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_943/*.txt", true);
        assertTrue(m.matches("pattern_943/file.txt"));
        assertFalse(m.matches("pattern_943/file.log"));
    }
    @Test
    public void testGlobCase_944() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_944/*.txt", true);
        assertTrue(m.matches("pattern_944/file.txt"));
        assertFalse(m.matches("pattern_944/file.log"));
    }
    @Test
    public void testGlobCase_945() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_945/*.txt", true);
        assertTrue(m.matches("pattern_945/file.txt"));
        assertFalse(m.matches("pattern_945/file.log"));
    }
    @Test
    public void testGlobCase_946() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_946/*.txt", true);
        assertTrue(m.matches("pattern_946/file.txt"));
        assertFalse(m.matches("pattern_946/file.log"));
    }
    @Test
    public void testGlobCase_947() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_947/*.txt", true);
        assertTrue(m.matches("pattern_947/file.txt"));
        assertFalse(m.matches("pattern_947/file.log"));
    }
    @Test
    public void testGlobCase_948() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_948/*.txt", true);
        assertTrue(m.matches("pattern_948/file.txt"));
        assertFalse(m.matches("pattern_948/file.log"));
    }
    @Test
    public void testGlobCase_949() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_949/*.txt", true);
        assertTrue(m.matches("pattern_949/file.txt"));
        assertFalse(m.matches("pattern_949/file.log"));
    }
    @Test
    public void testGlobCase_950() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_950/*.txt", true);
        assertTrue(m.matches("pattern_950/file.txt"));
        assertFalse(m.matches("pattern_950/file.log"));
    }
    @Test
    public void testGlobCase_951() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_951/*.txt", true);
        assertTrue(m.matches("pattern_951/file.txt"));
        assertFalse(m.matches("pattern_951/file.log"));
    }
    @Test
    public void testGlobCase_952() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_952/*.txt", true);
        assertTrue(m.matches("pattern_952/file.txt"));
        assertFalse(m.matches("pattern_952/file.log"));
    }
    @Test
    public void testGlobCase_953() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_953/*.txt", true);
        assertTrue(m.matches("pattern_953/file.txt"));
        assertFalse(m.matches("pattern_953/file.log"));
    }
    @Test
    public void testGlobCase_954() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_954/*.txt", true);
        assertTrue(m.matches("pattern_954/file.txt"));
        assertFalse(m.matches("pattern_954/file.log"));
    }
    @Test
    public void testGlobCase_955() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_955/*.txt", true);
        assertTrue(m.matches("pattern_955/file.txt"));
        assertFalse(m.matches("pattern_955/file.log"));
    }
    @Test
    public void testGlobCase_956() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_956/*.txt", true);
        assertTrue(m.matches("pattern_956/file.txt"));
        assertFalse(m.matches("pattern_956/file.log"));
    }
    @Test
    public void testGlobCase_957() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_957/*.txt", true);
        assertTrue(m.matches("pattern_957/file.txt"));
        assertFalse(m.matches("pattern_957/file.log"));
    }
    @Test
    public void testGlobCase_958() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_958/*.txt", true);
        assertTrue(m.matches("pattern_958/file.txt"));
        assertFalse(m.matches("pattern_958/file.log"));
    }
    @Test
    public void testGlobCase_959() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_959/*.txt", true);
        assertTrue(m.matches("pattern_959/file.txt"));
        assertFalse(m.matches("pattern_959/file.log"));
    }
    @Test
    public void testGlobCase_960() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_960/*.txt", true);
        assertTrue(m.matches("pattern_960/file.txt"));
        assertFalse(m.matches("pattern_960/file.log"));
    }
    @Test
    public void testGlobCase_961() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_961/*.txt", true);
        assertTrue(m.matches("pattern_961/file.txt"));
        assertFalse(m.matches("pattern_961/file.log"));
    }
    @Test
    public void testGlobCase_962() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_962/*.txt", true);
        assertTrue(m.matches("pattern_962/file.txt"));
        assertFalse(m.matches("pattern_962/file.log"));
    }
    @Test
    public void testGlobCase_963() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_963/*.txt", true);
        assertTrue(m.matches("pattern_963/file.txt"));
        assertFalse(m.matches("pattern_963/file.log"));
    }
    @Test
    public void testGlobCase_964() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_964/*.txt", true);
        assertTrue(m.matches("pattern_964/file.txt"));
        assertFalse(m.matches("pattern_964/file.log"));
    }
    @Test
    public void testGlobCase_965() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_965/*.txt", true);
        assertTrue(m.matches("pattern_965/file.txt"));
        assertFalse(m.matches("pattern_965/file.log"));
    }
    @Test
    public void testGlobCase_966() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_966/*.txt", true);
        assertTrue(m.matches("pattern_966/file.txt"));
        assertFalse(m.matches("pattern_966/file.log"));
    }
    @Test
    public void testGlobCase_967() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_967/*.txt", true);
        assertTrue(m.matches("pattern_967/file.txt"));
        assertFalse(m.matches("pattern_967/file.log"));
    }
    @Test
    public void testGlobCase_968() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_968/*.txt", true);
        assertTrue(m.matches("pattern_968/file.txt"));
        assertFalse(m.matches("pattern_968/file.log"));
    }
    @Test
    public void testGlobCase_969() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_969/*.txt", true);
        assertTrue(m.matches("pattern_969/file.txt"));
        assertFalse(m.matches("pattern_969/file.log"));
    }
    @Test
    public void testGlobCase_970() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_970/*.txt", true);
        assertTrue(m.matches("pattern_970/file.txt"));
        assertFalse(m.matches("pattern_970/file.log"));
    }
    @Test
    public void testGlobCase_971() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_971/*.txt", true);
        assertTrue(m.matches("pattern_971/file.txt"));
        assertFalse(m.matches("pattern_971/file.log"));
    }
    @Test
    public void testGlobCase_972() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_972/*.txt", true);
        assertTrue(m.matches("pattern_972/file.txt"));
        assertFalse(m.matches("pattern_972/file.log"));
    }
    @Test
    public void testGlobCase_973() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_973/*.txt", true);
        assertTrue(m.matches("pattern_973/file.txt"));
        assertFalse(m.matches("pattern_973/file.log"));
    }
    @Test
    public void testGlobCase_974() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_974/*.txt", true);
        assertTrue(m.matches("pattern_974/file.txt"));
        assertFalse(m.matches("pattern_974/file.log"));
    }
    @Test
    public void testGlobCase_975() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_975/*.txt", true);
        assertTrue(m.matches("pattern_975/file.txt"));
        assertFalse(m.matches("pattern_975/file.log"));
    }
    @Test
    public void testGlobCase_976() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_976/*.txt", true);
        assertTrue(m.matches("pattern_976/file.txt"));
        assertFalse(m.matches("pattern_976/file.log"));
    }
    @Test
    public void testGlobCase_977() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_977/*.txt", true);
        assertTrue(m.matches("pattern_977/file.txt"));
        assertFalse(m.matches("pattern_977/file.log"));
    }
    @Test
    public void testGlobCase_978() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_978/*.txt", true);
        assertTrue(m.matches("pattern_978/file.txt"));
        assertFalse(m.matches("pattern_978/file.log"));
    }
    @Test
    public void testGlobCase_979() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_979/*.txt", true);
        assertTrue(m.matches("pattern_979/file.txt"));
        assertFalse(m.matches("pattern_979/file.log"));
    }
    @Test
    public void testGlobCase_980() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_980/*.txt", true);
        assertTrue(m.matches("pattern_980/file.txt"));
        assertFalse(m.matches("pattern_980/file.log"));
    }
    @Test
    public void testGlobCase_981() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_981/*.txt", true);
        assertTrue(m.matches("pattern_981/file.txt"));
        assertFalse(m.matches("pattern_981/file.log"));
    }
    @Test
    public void testGlobCase_982() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_982/*.txt", true);
        assertTrue(m.matches("pattern_982/file.txt"));
        assertFalse(m.matches("pattern_982/file.log"));
    }
    @Test
    public void testGlobCase_983() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_983/*.txt", true);
        assertTrue(m.matches("pattern_983/file.txt"));
        assertFalse(m.matches("pattern_983/file.log"));
    }
    @Test
    public void testGlobCase_984() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_984/*.txt", true);
        assertTrue(m.matches("pattern_984/file.txt"));
        assertFalse(m.matches("pattern_984/file.log"));
    }
    @Test
    public void testGlobCase_985() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_985/*.txt", true);
        assertTrue(m.matches("pattern_985/file.txt"));
        assertFalse(m.matches("pattern_985/file.log"));
    }
    @Test
    public void testGlobCase_986() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_986/*.txt", true);
        assertTrue(m.matches("pattern_986/file.txt"));
        assertFalse(m.matches("pattern_986/file.log"));
    }
    @Test
    public void testGlobCase_987() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_987/*.txt", true);
        assertTrue(m.matches("pattern_987/file.txt"));
        assertFalse(m.matches("pattern_987/file.log"));
    }
    @Test
    public void testGlobCase_988() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_988/*.txt", true);
        assertTrue(m.matches("pattern_988/file.txt"));
        assertFalse(m.matches("pattern_988/file.log"));
    }
    @Test
    public void testGlobCase_989() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_989/*.txt", true);
        assertTrue(m.matches("pattern_989/file.txt"));
        assertFalse(m.matches("pattern_989/file.log"));
    }
    @Test
    public void testGlobCase_990() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_990/*.txt", true);
        assertTrue(m.matches("pattern_990/file.txt"));
        assertFalse(m.matches("pattern_990/file.log"));
    }
    @Test
    public void testGlobCase_991() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_991/*.txt", true);
        assertTrue(m.matches("pattern_991/file.txt"));
        assertFalse(m.matches("pattern_991/file.log"));
    }
    @Test
    public void testGlobCase_992() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_992/*.txt", true);
        assertTrue(m.matches("pattern_992/file.txt"));
        assertFalse(m.matches("pattern_992/file.log"));
    }
    @Test
    public void testGlobCase_993() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_993/*.txt", true);
        assertTrue(m.matches("pattern_993/file.txt"));
        assertFalse(m.matches("pattern_993/file.log"));
    }
    @Test
    public void testGlobCase_994() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_994/*.txt", true);
        assertTrue(m.matches("pattern_994/file.txt"));
        assertFalse(m.matches("pattern_994/file.log"));
    }
    @Test
    public void testGlobCase_995() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_995/*.txt", true);
        assertTrue(m.matches("pattern_995/file.txt"));
        assertFalse(m.matches("pattern_995/file.log"));
    }
    @Test
    public void testGlobCase_996() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_996/*.txt", true);
        assertTrue(m.matches("pattern_996/file.txt"));
        assertFalse(m.matches("pattern_996/file.log"));
    }
    @Test
    public void testGlobCase_997() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_997/*.txt", true);
        assertTrue(m.matches("pattern_997/file.txt"));
        assertFalse(m.matches("pattern_997/file.log"));
    }
    @Test
    public void testGlobCase_998() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_998/*.txt", true);
        assertTrue(m.matches("pattern_998/file.txt"));
        assertFalse(m.matches("pattern_998/file.log"));
    }
    @Test
    public void testGlobCase_999() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_999/*.txt", true);
        assertTrue(m.matches("pattern_999/file.txt"));
        assertFalse(m.matches("pattern_999/file.log"));
    }
    @Test
    public void testGlobCase_1000() {
        GlobDfaMatcher m = GlobCompiler.compile("pattern_1000/*.txt", true);
        assertTrue(m.matches("pattern_1000/file.txt"));
        assertFalse(m.matches("pattern_1000/file.log"));
    }
}

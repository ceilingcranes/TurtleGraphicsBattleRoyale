package test;

import main.Command;
import main.LocationChange;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.stream.Location;

import static org.testng.Assert.*;

public class CommandTest {
    Command c = new Command();
//    @Test
//    public void testCo

    @Test
    public void testToString() {
        c.addCommands("move 2");
        String ret = c.toString();
        Assert.assertTrue(ret.contains("move 2"));
    }

    @Test
    public void testGetNextLocation() {
        String test_commands = "move 2\nturn 3\n turn -3\nmove 10";
        c.resetCommands();
        c.addCommands(test_commands);

        LocationChange l1 = c.getNextLocation();
        Assert.assertEquals(l1.getMove(), 2);
        Assert.assertEquals(l1.getTurn(), 0);

        LocationChange l2 = c.getNextLocation();
        Assert.assertEquals(l2.getMove(), 0);
        Assert.assertEquals(l2.getTurn(), 3);

        LocationChange l3 = c.getNextLocation();
        Assert.assertEquals(l3.getMove(), 0);
        Assert.assertEquals(l3.getTurn(), -3);

        LocationChange l4 = c.getNextLocation();
        Assert.assertEquals(l4.getMove(), 10);
        Assert.assertEquals(l4.getTurn(), 0);

    }

    @Test
    public void resetCommands(){
        c.resetCommands();
        Assert.assertTrue(c.isEmpty());
        Assert.assertEquals(c.toString(), "");
    }

    @Test
    public void testGenerateLocationChange_move(){
        String testString = "move 2";
        LocationChange l = c.generateLocationChange(testString);
        Assert.assertEquals(l.getMove(), 2);
    }

    @Test
    public void testGenerateLocationChange_turn_positive(){
        String testString = "turn 2";
        LocationChange l = c.generateLocationChange(testString);
        Assert.assertEquals(l.getTurn(), 2);
    }

    @Test
    public void testGenerateLocationChange_turn_negative(){
        String testString = "turn -2";
        LocationChange l = c.generateLocationChange(testString);
        Assert.assertEquals(l.getTurn(), -2);
    }


    @Test
    public void testValidateCommand_valid_turn(){
        String validCommand = "turn -22";
        Assert.assertTrue(c.validateCommand(validCommand));
    }

    @Test
    public void testValidateCommand_valid_move(){
        String validCommand = "move 30";
        Assert.assertTrue(c.validateCommand(validCommand));
    }

    @Test
    public void testValidateCommand_invalid_turn(){
        String invalidCommand = "turn2";
        Assert.assertFalse(c.validateCommand(invalidCommand));
    }

    @Test
    public void testValidateCommand_invalid_move(){
        String invalidCommand = "move2";
        Assert.assertFalse(c.validateCommand(invalidCommand));
    }

    /*@Test
    public void testGenerateLocationChange_invalid_turn(){
        String testString = "turn --2";
        Assert.assertThrows(c.generateLocationChange(testString));
    }*/
}
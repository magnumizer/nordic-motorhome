package Model;//Magnus Svendsen DAT16i

public class LoginHandler
{
    private int loginAttempts;
    private final int maxAttempts = 5;

    StageHandler stageHandler = new StageHandler();

    public boolean handleLogin(String username, String password)
    {
        boolean foundEmployee = false;

        for (Employee employee : Employee.allEmployees)
        {
            if (employee.getUsername().equals(username))
            {
                if (employee.getPassword().equals(password))
                {
                    StageHandler.currentUser = employee;
                    foundEmployee = true;
                    break;
                }
                else
                {
                    return wrongPassword();
                }
            }
        }

        return foundEmployee;
    }

    private boolean wrongPassword()
    {
        loginAttempts++;

        if (loginAttempts >= maxAttempts)
        {
            System.out.println("Too many login attempts. Contacting administrator. Closing program.");
            //Set up logic for contacting admin here (or call method from other class?)

            stageHandler.displayError("Breach suspected", "Too many login attempts. Contacting administrator.", "Closing program.");

            Main.primaryStage.hide();
            Main.primaryStage.close();
        }

        return false;
    }
}

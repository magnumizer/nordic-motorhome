package Model;//Magnus Svendsen DAT16i

public class LoginHandler
{
    private int passAttempts;
    private int maxAttempts = 5;

    public boolean handlePassword(String position, String username, String password)
    {
        //handle password logic here
        switch (position)
        {
            case "Admin":
                if (AccountHandler.adminAccounts.containsKey(username))
                {
                    if (AccountHandler.adminAccounts.get(username).equals(password))
                    {
                        return true;
                    }
                    else
                    {
                        return wrongPassword();
                    }
                }
                else
                {
                    return false;
                }
            case "Sales Assistant":
                if (AccountHandler.salesAssistantAccounts.containsKey(username))
                {
                    if (AccountHandler.salesAssistantAccounts.get(username).equals(password))
                    {
                        return true;
                    }
                    else
                    {
                        return wrongPassword();
                    }
                }
                else
                {
                    return false;
                }
            case "Auto Mechanic":
                if (AccountHandler.autoMechanicAccounts.containsKey(username))
                {
                    if (AccountHandler.autoMechanicAccounts.get(username).equals(password))
                    {
                        return true;
                    }
                    else
                    {
                        return wrongPassword();
                    }
                }
                else
                {
                    return false;
                }
                default:
                System.out.println("Error in parsing Employee position. Check if there is a mistake in the reference.");
                return false;
        }
    }

    private boolean wrongPassword()
    {
        System.out.println("Error. Wrong password.");

        passAttempts++;

        if (passAttempts >= maxAttempts)
        {
            System.out.println("Too many login attempts. Contacting administrator. Closing program.");
            //Set up logic for contacting admin here (or call method from other class?)

            Main.primaryStage.hide();
            Main.primaryStage.close();
            //System.exit(0);
        }

        return false;
    }
}

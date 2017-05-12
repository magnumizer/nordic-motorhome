package Model;//Magnus Svendsen DAT16i

public class LoginHandler
{
    private int passAttempts;
    private int maxAttempts = 5;

    public boolean handleLogin(String position, String username, String password)
    {
        boolean foundEmployee = false;

        switch (position)
        {
            case "Admin":

                for (Employee employee : Employee.allEmployees)
                {
                    if (employee instanceof Admin)
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
                }

                if (foundEmployee)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "Sales Assistant":

                for (Employee employee : Employee.allEmployees)
                {
                    if (employee instanceof SalesAssistant)
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
                }

                if (foundEmployee)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "Auto Mechanic":

                for (Employee employee : Employee.allEmployees)
                {
                    if (employee instanceof AutoMechanic)
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
                }

                if (foundEmployee)
                {
                    return true;
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

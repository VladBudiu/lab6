package exercitiu1;

import java.time.LocalDate;

public class Employee
{
    public Employee() {}

    public Employee(final String name, final String job,
                    final LocalDate dateOfEmployment, final float wage)
    {
        name_ = name;
        job_ = job;
        dateOfEmployment_ = dateOfEmployment;
        wage_ = wage;
    }

    public float getWage()
    {
        return wage_;
    }

    public LocalDate getDateOfEmployment()
    {
        return dateOfEmployment_;
    }

    public String getJob()
    {
        return job_;
    }

    public String getName()
    {
        return name_;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment)
    {
        dateOfEmployment_ = dateOfEmployment;
    }

    public void setJob(String job)
    {
        job_ = job;
    }

    public void setName(String name)
    {
        name_ = name;
    }

    public void setWage(float wage)
    {
        wage_ = wage;
    }

    @Override
    public String toString()
    {
        return "Name: " + name_ + "\n" +
                "Job: " + job_ + "\n" +
                "Date of employment: " + dateOfEmployment_ + "\n" +
                "Wage: " + wage_ + "\n";
    }

    private String name_;
    private String job_;
    private LocalDate dateOfEmployment_;
    private float wage_;

    public int compareTo(Employee b)
    {
        if (wage_ < b.wage_)
        {
            return -1;
        }
        if (wage_ == b.wage_)
        {
            return 0;
        }
        return 1;
    }
}

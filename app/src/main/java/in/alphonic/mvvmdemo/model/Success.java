package in.alphonic.mvvmdemo.model;

public class Success {
    private Data[] data;

    private String status;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public class Data
    {
        private String customer_support_number;

        private String driver_support_number;

        private String customer_app_version;

        private String driver_registration_charge;

        private String driver_app_version;

        public String getCustomer_support_number ()
        {
            return customer_support_number;
        }

        public void setCustomer_support_number (String customer_support_number)
        {
            this.customer_support_number = customer_support_number;
        }

        public String getDriver_support_number ()
        {
            return driver_support_number;
        }

        public void setDriver_support_number (String driver_support_number)
        {
            this.driver_support_number = driver_support_number;
        }

        public String getCustomer_app_version ()
        {
            return customer_app_version;
        }

        public void setCustomer_app_version (String customer_app_version)
        {
            this.customer_app_version = customer_app_version;
        }

        public String getDriver_registration_charge ()
        {
            return driver_registration_charge;
        }

        public void setDriver_registration_charge (String driver_registration_charge)
        {
            this.driver_registration_charge = driver_registration_charge;
        }

        public String getDriver_app_version ()
        {
            return driver_app_version;
        }

        public void setDriver_app_version (String driver_app_version)
        {
            this.driver_app_version = driver_app_version;
        }

    }

}

class Admin
{
    def name = "vinothkumar"
    def task = "Created shared Library"
    Admin()
    {
        println name
    }
    void run()
    {
        print task
    }
    
    static void main(String )
    {
        Admin admin = new Admin();
        admin.run();
    
        
    }
}
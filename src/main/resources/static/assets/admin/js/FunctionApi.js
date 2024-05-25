getDataFormApi();

async function getDataFormApi(){
    const UserData = await fetch('http://localhost:8080/dashboard/admin/list')
    console.log('UserData :>> ', UserData);
    const data = await UserData.json();
    console.log('data:>>' ,data)
}
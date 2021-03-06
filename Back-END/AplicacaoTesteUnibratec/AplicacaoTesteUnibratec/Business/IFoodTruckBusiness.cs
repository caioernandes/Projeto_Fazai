﻿using AplicacaoTesteUnibratec.Models.Mobile;
using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface IFoodTruckBusiness
    {
        List<FoodTruck> GetAll(string listaIdFoodTruckFuncionario);
        void Insert(FoodTruck foodTruck);
        void Delete(int id);
        void Update(FoodTruck foodTruck);
        FoodTruck GetForId(int id);

        #region Region - Mobile

        List<Estabelecimento> GetAllMobile(string cidade);
        Estabelecimento GetForIdMobile(int id); 

        #endregion
    }
}
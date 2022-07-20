package ru.ozon.route256.util.presentation.viewholder

interface ViewHolderModel {
    infix fun areItemsTheSame(otherViewHolderModel: ViewHolderModel): Boolean = this == otherViewHolderModel

    infix fun areContentsTheSame(otherViewHolderModel: ViewHolderModel): Boolean = this == otherViewHolderModel
}

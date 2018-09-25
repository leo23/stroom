/*
 * Deleteright 2018 Crown Deleteright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { Action, ActionCreator } from "redux";

import {
  prepareReducerById,
  StateById,
  ActionId
} from "../../../lib/redux-actions-ts";
import { DOC_REFS_DELETED, DocRefsDeleted } from "./documentTree";

const PREPARE_DOC_REF_DELETE = "PREPARE_DOC_REF_DELETE";
const COMPLETE_DOC_REF_DELETE = "COMPLETE_DOC_REF_DELETE";

export interface PrepareDocRefDelete
  extends ActionId,
    Action<"PREPARE_DOC_REF_DELETE"> {
  uuids: Array<string>;
  destinationUuid: string;
}

export interface CompleteDocRefDelete
  extends ActionId,
    Action<"COMPLETE_DOC_REF_DELETE"> {}

export interface ActionCreators {
  prepareDocRefDelete: ActionCreator<PrepareDocRefDelete>;
  completeDocRefDelete: ActionCreator<CompleteDocRefDelete>;
}

export const actionCreators: ActionCreators = {
  prepareDocRefDelete: (id, uuids, destinationUuid) => ({
    type: PREPARE_DOC_REF_DELETE,
    id,
    uuids,
    destinationUuid
  }),
  completeDocRefDelete: id => ({ type: COMPLETE_DOC_REF_DELETE, id })
};

export interface StoreStatePerId {
  isDeleting: boolean;
  uuids: Array<string>;
}

export interface StoreState extends StateById<StoreStatePerId> {}

// The state will contain a map of arrays.
// Keyed on explorer ID, the arrays will contain the doc refs being moved
export const defaultStatePerId: StoreStatePerId = {
  isDeleting: false,
  uuids: []
};

export const reducer = prepareReducerById(defaultStatePerId)
  .handleAction<PrepareDocRefDelete>(
    PREPARE_DOC_REF_DELETE,
    (state, { uuids, destinationUuid }: PrepareDocRefDelete) => ({
      isDeleting: uuids.length > 0,
      uuids,
      destinationUuid
    })
  )
  .handleAction<CompleteDocRefDelete>(
    COMPLETE_DOC_REF_DELETE,
    () => defaultStatePerId
  )
  .handleForeignAction<DocRefsDeleted>(
    DOC_REFS_DELETED,
    () => defaultStatePerId
  )
  .getReducer();